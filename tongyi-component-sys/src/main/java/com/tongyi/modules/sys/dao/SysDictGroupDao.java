/*
 * 项目名称:tongyi-component
 * 类名称:SysDictGroupDao.java
 * 包名称:com.tongyi.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-22 10:32:28        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.sys.entity.SysDictGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据字典分组Dao
 *
 * @author 林佛权
 */
@Mapper
public interface SysDictGroupDao extends BaseMapper<SysDictGroupEntity> {

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysDictGroupEntity> selectSysDictGroupPage(IPage page, @Param("params") Map<String, Object> params);

    /**
     * 查询所有数据字典
     *
     * @param params 查询参数
     * @return List
     */
    List<SysDictGroupEntity> queryAll(@Param("params") Map<String, Object> params);
}
