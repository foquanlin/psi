package com.tongyi.modules.gen.service;

import com.tongyi.core.IService;
import com.tongyi.modules.gen.entity.ColumnEntity;
import com.tongyi.modules.gen.entity.ResultMapEntity;

import java.util.List;

/**
 * 代码生成器
 *
 * @author 林佛权
 * @email 147657060@qq.com
 */
public interface SysGeneratorService extends IService<ResultMapEntity> {

    /**
     * queryTable
     *
     * @param tableName 表名
     * @return ResultMapEntity
     */
    ResultMapEntity queryTable(String tableName);

    /**
     * queryColumns
     *
     * @param tableName 表名
     * @return List
     */
    List<ColumnEntity> queryColumns(String tableName);

    /**
     * 生成代码
     *
     * @param tableNames  表名
     * @param projectName 项目名称
     * @param packageName 报名
     * @param author      作者
     * @return byte[]
     */
    byte[] generatorCode(String[] tableNames, String projectName, String packageName, String author);
}
