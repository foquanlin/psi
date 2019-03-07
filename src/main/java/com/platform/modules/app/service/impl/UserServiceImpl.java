/*
 * 项目名称:platform-plus
 * 类名称:UserServiceImpl.java
 * 包名称:com.platform.modules.app.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.platform.common.utils.JedisUtil;
import com.platform.common.validator.AbstractAssert;
import com.platform.modules.app.dao.UserDao;
import com.platform.modules.app.entity.UserEntity;
import com.platform.modules.app.service.UserService;
import com.platform.modules.app.utils.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    private WechatUtil wechatUtil;

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
    public UserEntity getWxUserInfoByOpenId(String openId) {
        //从redis获取accessToken
        String accessToken = ((Map) jedisUtil.getObject(Constant.ACCESS_TOKEN)).get("access_token").toString();
        // 获取用户信息
        return wechatUtil.getUserInfo(openId, accessToken, 3);
    }

    @Override
    public UserEntity selectByOpenId(String openId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenId(openId);
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("OPEN_ID", openId));
    }

    @Override
    public UserEntity saveOrUpdateByOpenId(UserEntity user) {
        if (null == selectByOpenId(user.getOpenId())) {
            this.save(user);
        } else {
            this.update(user, new UpdateWrapper<UserEntity>().eq("OPEN_ID", user.getOpenId()));
        }
        return user;
    }
}
