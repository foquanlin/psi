package com.tongyi.modules.gen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.*;
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

    @Override
    public ResultMapEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<ResultMapEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<ResultMapEntity> listPage(int current, int size,Map<String, Object> params) {
        params.put("driverClassName", driverClassName);
        Page<ResultMapEntity> page = new Query<ResultMapEntity>(current,size,params).getPage();
        List<ResultMapEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<ResultMapEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(ResultMapEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(ResultMapEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }
}
