/*
 * 项目名称:tongyi-component
 * 类名称:ActReModelDao.java
 * 包名称:com.tongyi.modules.act.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.act.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongyi.modules.act.entity.ActReModelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao
 *
 * @author 林佛权
 */
@Mapper
public interface ActReModelDao extends BaseMapper<ActReModelEntity> {

}
