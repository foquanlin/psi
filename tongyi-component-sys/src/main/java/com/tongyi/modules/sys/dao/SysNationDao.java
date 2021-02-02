/*
 * 项目名称:项目名称
 * 类名称:SysNationDao.java
 * 包名称:com.tongyi.modules.sys.dao
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 * Copyright (c) 2019-2019 惠州市酷天科技有限公司
 */
package com.tongyi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.sys.entity.SysNationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 民族Dao
 *
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 */
@Mapper
public interface SysNationDao extends BaseMapper<SysNationEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysNationEntity> queryAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysNationEntity> selectSysNationPage(IPage page, @Param("params") Map<String, Object> params);
}
