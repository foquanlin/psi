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
package com.platform.modules.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.JedisUtil;
import com.platform.common.utils.Query;
import com.platform.common.validator.AbstractAssert;
import com.platform.modules.mall.dao.MallUserDao;
import com.platform.modules.mall.entity.MallUserEntity;
import com.platform.modules.mall.service.MallUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("userService")
public class MallUserServiceImpl extends ServiceImpl<MallUserDao, MallUserEntity> implements MallUserService {
    @Autowired
    JedisUtil jedisUtil;

    @Override
    public List<MallUserEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.REGISTER_TIME");
        params.put("asc", false);
        Page<MallUserEntity> page = new Query<MallUserEntity>(params).getPage();
        return page.setRecords(baseMapper.selectMallUserPage(page, params));
    }

    @Override
    public boolean add(MallUserEntity mallUser) {
        return this.save(mallUser);
    }

    @Override
    public boolean update(MallUserEntity mallUser) {
        return this.updateById(mallUser);
    }

    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(String[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public MallUserEntity queryByMobile(String mobile) {
        return this.getOne(new QueryWrapper<MallUserEntity>().eq("MOBILE", mobile), false);
    }

    @Override
    public MallUserEntity loginByMobile(String mobile, String password) {
        MallUserEntity user = queryByMobile(mobile);
        AbstractAssert.isNull(user, "该手机暂未绑定用户");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new BusinessException("手机号或密码错误");
        }

        return user;
    }

    @Override
    public MallUserEntity selectByOpenId(String openId) {
        MallUserEntity userEntity = new MallUserEntity();
        userEntity.setOpenId(openId);
        return baseMapper.selectOne(new QueryWrapper<MallUserEntity>().eq("OPEN_ID", openId));
    }

    @Override
    public MallUserEntity saveOrUpdateByOpenId(WxMpUser userWxInfo) {
        MallUserEntity entity = this.getOne(new QueryWrapper<MallUserEntity>().eq("OPEN_ID", userWxInfo.getOpenId()));
        if (entity == null) {
            entity = new MallUserEntity();
            entity.setRegisterTime(new Date());
        }
        entity.setNickname(userWxInfo.getNickname());
        entity.setHeadImgUrl(userWxInfo.getHeadImgUrl());
        entity.setOpenId(userWxInfo.getOpenId());
        entity.setGender(userWxInfo.getSex());
        this.saveOrUpdate(entity);
        return entity;
    }
}
