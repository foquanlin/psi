package com.tongyi.modules.gen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.gen.dao.SysGeneratorDao;
import com.tongyi.modules.gen.entity.ColumnEntity;
import com.tongyi.modules.gen.entity.ForeignEntity;
import com.tongyi.modules.gen.entity.ResultMapEntity;
import com.tongyi.modules.gen.entity.TableEntity;
import com.tongyi.modules.gen.service.SysGeneratorService;
import com.tongyi.modules.gen.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author 林佛权
 */
@Service("sysGeneratorService")
public class SysGeneratorServiceImpl extends ServiceImpl<SysGeneratorDao, ResultMapEntity> implements SysGeneratorService {
    private static Logger logger = LoggerFactory.getLogger(SysGeneratorServiceImpl.class);

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Override
    public Page queryPage(Map<String, Object> params) {
        Page<ResultMapEntity> page = new Query<ResultMapEntity>(params).getPage();

        params.put("driverClassName", driverClassName);
        return page.setRecords(baseMapper.queryPage(page, params));
    }

    @Override
    public ResultMapEntity queryTable(String tableName) {
        Map<String, Object> params = new HashMap<>(4);

        params.put("tableName", tableName);
        params.put("driverClassName", driverClassName);

        return baseMapper.queryTable(params);
    }

    @Override
    public List<ColumnEntity> queryColumns(String tableName) {
        Map<String, Object> params = new HashMap<>(4);

        params.put("tableName", tableName);
        params.put("driverClassName", driverClassName);

        List<ColumnEntity> columns = baseMapper.queryColumns(params);
        for(ColumnEntity entity:columns){
            entity.setColumnDefault(entity.getColumnDefault()==null?"''":String.format("'%s'",entity.getColumnDefault()));
            entity.setRequired("YES".equalsIgnoreCase(entity.getIsNullable())?false:true);
        }
        logger.info(ReflectionToStringBuilder.reflectionToString(columns, ToStringStyle.SIMPLE_STYLE));
        return columns;
    }

    private List<ForeignEntity> queryForeigns(String tableName){
        Map<String, Object> params = new HashMap<>(4);

        params.put("tableName", tableName);
        params.put("driverClassName", driverClassName);
        List<ForeignEntity> columns = baseMapper.queryForeign(params);
        logger.info(ReflectionToStringBuilder.reflectionToString(columns, ToStringStyle.SIMPLE_STYLE));
        return columns;
    }

    @Override
    public byte[] generatorCode(String[] tableNames, String projectName, String packageName, String author) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            ResultMapEntity table = queryTable(tableName);
            //查询列信息
            List<ColumnEntity> columns = queryColumns(tableName);
            List<ForeignEntity> foreigns = queryForeigns(tableName);
            //生成代码
            String tablePrefix = tableName.split("_")[0];
            TableEntity tableEntity = GenUtils.builder(table,columns,foreigns);
            GenUtils.generatorCode(tableEntity, zip, projectName, packageName, author, tablePrefix);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
