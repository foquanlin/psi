/*
 * 项目名称:platform-plus
 * 类名称:SysConfigDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author 李鹏军
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     *
     * @param paramKey key
     * @return SysConfigEntity
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     *
     * @param paramKey   key
     * @param paramValue value
     * @return int
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);
}
