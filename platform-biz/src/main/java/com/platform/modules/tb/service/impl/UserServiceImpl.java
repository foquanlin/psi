/*
 * 项目名称:platform-boot
 * 类名称:UserServiceImpl.java
 * 包名称:com.platform.modules.app.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.tb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.JedisUtil;
import com.platform.common.validator.AbstractAssert;
import com.platform.modules.app.entity.UserEntity;
import com.platform.modules.tb.dao.UserDao;
import com.platform.modules.tb.service.UserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 李鹏军
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    JedisUtil jedisUtil;

    @Override
    public UserEntity queryByMobile(String mobile) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("MOBILE", mobile));
    }

    @Override
    public String login(String mobile, String password) {
        UserEntity user = queryByMobile(mobile);
        AbstractAssert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new BusinessException("手机号或密码错误");
        }

        return user.getUserId();
    }

    @Override
    public UserEntity selectByOpenId(String openId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenId(openId);
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("OPEN_ID", openId));
    }

    @Override
    public UserEntity saveOrUpdateByOpenId(WxMpUser userWxInfo) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("OPEN_ID", userWxInfo.getOpenId()));
        if (entity == null) {
            entity = new UserEntity();
            entity.setCreateTime(new Date());
        }
        entity.setNickname(userWxInfo.getNickname());
        entity.setHeadImgUrl(userWxInfo.getHeadImgUrl());
        entity.setOpenId(userWxInfo.getOpenId());
        entity.setSex(userWxInfo.getSex());
        this.saveOrUpdate(entity);
        return entity;
    }
}
