/*
 * 项目名称:platform-plus
 * 类名称:SysCaptchaDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author 林佛权
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
