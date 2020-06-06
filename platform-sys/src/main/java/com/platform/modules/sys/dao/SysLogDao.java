/*
 * 项目名称:platform-plus
 * 类名称:SysLogDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-22 10:36:49        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志Dao
 *
 * @author 林佛权
 * @date 2019-01-22 10:36:49
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
