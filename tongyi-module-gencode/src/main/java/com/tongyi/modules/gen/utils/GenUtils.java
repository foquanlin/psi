package com.tongyi.modules.gen.utils;

import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.DateUtils;
import com.tongyi.modules.gen.entity.ColumnEntity;
import com.tongyi.modules.gen.entity.ForeignEntity;
import com.tongyi.modules.gen.entity.ResultMapEntity;
import com.tongyi.modules.gen.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author 林佛权
 * @email 147657060@qq.com
 */
public class GenUtils {

    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("gen/template/Entity.java.vm");
        templates.add("gen/template/Dao.java.vm");
        templates.add("gen/template/Dao.xml.vm");
        templates.add("gen/template/Service.java.vm");
        templates.add("gen/template/ServiceImpl.java.vm");
        templates.add("gen/template/Controller.java.vm");
        templates.add("gen/template/ServiceTest.java.vm");
        templates.add("gen/template/MockTest.java.vm");
        templates.add("gen/template/menu.sql.vm");
        templates.add("gen/template/vue.vm");
        templates.add("gen/template/edit.vue.vm");
        return templates;
    }

    public static TableEntity builder(ResultMapEntity table,
                               List<ColumnEntity> columns,
                               List<ForeignEntity> foreigns){
        //配置信息
        Configuration config = getConfig();

        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.getTableName());
        tableEntity.setComments(table.getTableComment());
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName());
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        tableEntity.setForeigns(foreigns);
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
//        boolean hasDate = false;
//        boolean hasBigDecimal = false;
        for (ColumnEntity column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.getColumnName().toLowerCase());
            columnEntity.setDataType(column.getDataType());
            columnEntity.setComments(null == column.getComments() ? column.getColumnName() : column.getComments());
            columnEntity.setColumnDefault(column.getColumnDefault());
            columnEntity.setRequired(column.isRequired());
            columnEntity.setIsNullable(column.getIsNullable());
            columnEntity.setDatetimePrecision(column.getDatetimePrecision());
            columnEntity.setOrdinalPosition(column.getOrdinalPosition());
            columnEntity.setNumericPrecision(column.getNumericPrecision());
            columnEntity.setNumericScale(column.getNumericScale());
            columnEntity.setExtra(column.getExtra());
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setGetMethod("get" + attrName + "()");
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType().split("\\(")[0], "unknowType");
            if ("number(22)".equalsIgnoreCase(columnEntity.getDataType())) {
                attrType = "Integer";
            }
            columnEntity.setAttrType(attrType);

            if ("Date".equals(attrType)) {
//                hasDate = true;
                tableEntity.setHasDate(true);
            }
            if ("BigDecimal".equals(attrType)) {
//                hasBigDecimal = true;
                tableEntity.setHasBigDecimal(true);
            }
            //是否主键
            if ((column.getColumnName().equalsIgnoreCase(column.getColumnKey()) && tableEntity.getPk() == null)) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //若没主键
        if (tableEntity.getPk() == null) {
            boolean flag = true;
            //设置columnName为id的为主键
            for (ColumnEntity columnEntity : tableEntity.getColumns()) {
                if ("id".equals(columnEntity.getAttrname())) {
                    tableEntity.setPk(columnEntity);
                    flag = false;
                    break;
                }
            }
            //若无id字段则第一个字段为主键
            if (flag) {
                tableEntity.setPk(tableEntity.getColumns().get(0));
            }
        }
        return tableEntity;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(TableEntity tableEntity,
                                     ZipOutputStream zip,
                                     String projectName,
                                     String packageName,
                                     String author,
                                     String tablePrefix) {

        String pre = tablePrefix.replace("_", "").toLowerCase();
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //封装模板数据
        /*
         * map中14个元素，与2的n次方最接近的数是16，但是这里如果设置容量为16的话 14/16=0.875,
         * 已经超过默认加载因子(0.75)的大小了。因此会resize一次，变成32。所以最好的结果还是32。
         */
        Map<String, Object> map = new HashMap<>(32);
        //取表前缀用于分包
        map.put("tableName", tableEntity.getTableName().toLowerCase());
        map.put("comments", null == tableEntity.getComments() ? "" : tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().replaceFirst(pre, "").toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", packageName + "." + pre);
        map.put("projectName", projectName);
        map.put("author", author);
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("pre", pre);
        map.put("hasDate", tableEntity.isHasDate());
        map.put("hasBigDecimal", tableEntity.isHasBigDecimal());

        codeFactory(map,tablePrefix, tableEntity, packageName, zip);
    }

    public static void codeFactory(Map<String, Object> map,String tablePrefix, TableEntity tableEntity, String packageName, ZipOutputStream zip) {
        String pre = tablePrefix.replace("_", "").toLowerCase();
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(Objects.requireNonNull(getFileName(template, tableEntity.getClassName(), packageName, pre))));
                System.out.println(sw.toString());
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    /**
     * 列名转换成Java属性名
     */
    private static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    private static String tableToJava(String tableName) {
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("gen/generator.properties");
        } catch (ConfigurationException e) {
            throw new BusinessException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    private static String getFileName(String template, String className, String packageName, String tablePrefix) {

        String strEJV = "Entity.java.vm";
        String strDJV = "Dao.java.vm";
        String strDXV = "Dao.xml.vm";
        String strSJV = "Service.java.vm";
        String strSIJV = "ServiceImpl.java.vm";
        String strCJV = "Controller.java.vm";
        String strMockTest = "MockTest.java.vm";
        String strServiceTest = "ServiceTest.java.vm";
        String strAouVV = "edit.vue.vm";
        String strVV = "vue.vm";
        String strMSV = "menu.sql.vm";

        String packagePath = "main" + File.separator + "java" + File.separator;
        String testPath = "test" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + tablePrefix + File.separator;
            testPath += packageName.replace(".", File.separator) + File.separator + tablePrefix + File.separator;
        }

        if (template.contains(strEJV)) {
            return String.format("tongyi-%s-core/src",tablePrefix) +File.separator + packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains(strDJV)) {
            return String.format("tongyi-%s-service/src",tablePrefix) +File.separator + packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains(strSJV)) {
            return String.format("tongyi-%s-core/src",tablePrefix) +File.separator + packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains(strSIJV)) {
            return String.format("tongyi-%s-service/src",tablePrefix) +File.separator + packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains(strCJV)) {
            return String.format("tongyi-%s-admin/src",tablePrefix) +File.separator + packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains(strMockTest)) {
            return String.format("tongyi-%s-admin/src",tablePrefix) +File.separator + testPath  + File.separator + className + "MockTest.java";
        }
        if (template.contains(strServiceTest)) {
            return String.format("tongyi-%s-admin/src",tablePrefix) +File.separator + testPath  + File.separator + className + "ServiceTest.java";
        }

        if (template.contains(strDXV)) {
            return String.format("tongyi-%s-service/src",tablePrefix) +File.separator + packagePath + "dao" + File.separator + className + "Dao.xml";
//            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + tablePrefix + File.separator + className + "Dao.xml";
        }

        if (template.contains(strAouVV)) {
            return "tongyi-ui/src/views/modules" + File.separator + tablePrefix + File.separator + className.toLowerCase().replaceFirst(tablePrefix, "") + "-edit.vue";
        }

        if (template.contains(strVV)) {
            return "tongyi-ui/src/views/modules" + File.separator + tablePrefix + File.separator + className.toLowerCase().replaceFirst(tablePrefix, "") + ".vue";
        }

        if (template.contains(strMSV)) {
            return String.format("tongyi-%s-admin",tablePrefix) +File.separator + "sql" + File.separator + className + ".sql";
        }

        return null;
    }
}
