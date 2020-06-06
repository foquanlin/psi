/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefDao.java
 * 包名称:com.platform.modules.act.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.act.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.act.entity.ActReProcdefEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao
 *
 * @author 林佛权
 * @date 2019-03-18 09:47:54
 */
@Mapper
public interface ActReProcdefDao extends BaseMapper<ActReProcdefEntity> {

}
