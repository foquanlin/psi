package com.tongyi.modules.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.gen.entity.ColumnEntity;
import com.tongyi.modules.gen.entity.ForeignEntity;
import com.tongyi.modules.gen.entity.ResultMapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author 林佛权
 * @email 147657060@qq.com
 */
@Mapper
public interface SysGeneratorDao extends BaseMapper<ResultMapEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<ResultMapEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<ResultMapEntity> listPage(IPage page, @Param("params") Map<String, Object> params);

    /**
     * queryTable
     *
     * @param params 查询参数
     * @return ResultMapEntity
     */
    ResultMapEntity queryTable(Map<String, Object> params);

    /**
     * queryColumns
     *
     * @param params 查询参数
     * @return List
     */
    List<ColumnEntity> queryColumns(Map<String, Object> params);

    /**
     * 查询关联外键的字段
     * @param params
     * @return
     */
    List<ForeignEntity> queryForeign(Map<String, Object> params);
}
