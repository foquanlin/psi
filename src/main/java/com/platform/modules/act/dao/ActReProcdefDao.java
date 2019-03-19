/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefDao.java
 * 包名称:com.platform.modules.act.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.modules.act.entity.ActReProcdefEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author 李鹏军
 * @date 2019-03-18 09:47:54
 */
@Mapper
public interface ActReProcdefDao extends BaseMapper<ActReProcdefEntity> {

    /**
     * 自定义分页查询，只查出最新的版本
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<ActReProcdefEntity> selectLatestVersion(IPage page, @Param("params") Map<String, Object> params);
}
