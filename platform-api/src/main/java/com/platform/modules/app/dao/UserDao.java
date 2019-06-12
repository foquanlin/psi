/*
 * 项目名称:platform-plus
 * 类名称:UserDao.java
 * 包名称:com.platform.modules.app.dao
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author 李鹏军
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
