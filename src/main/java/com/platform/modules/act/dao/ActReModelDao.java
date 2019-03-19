/*
 * 项目名称:platform-plus
 * 类名称:ActReModelDao.java
 * 包名称:com.platform.modules.act.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.modules.act.entity.ActReModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author 李鹏军
 * @date 2019-03-18 13:33:07
 */
@Mapper
public interface ActReModelDao extends BaseMapper<ActReModelEntity> {

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<ActReModelEntity> selectActReModelPage(IPage page, @Param("params") Map<String, Object> params);
}
