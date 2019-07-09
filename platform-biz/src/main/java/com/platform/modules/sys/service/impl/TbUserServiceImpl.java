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
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.JedisUtil;
import com.platform.common.utils.Query;
import com.platform.common.validator.AbstractAssert;
import com.platform.modules.sys.dao.TbUserDao;
import com.platform.modules.sys.entity.TbUserEntity;
import com.platform.modules.sys.service.TbUserService;
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
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {
    @Autowired
    JedisUtil jedisUtil;

    @Override
    public List<TbUserEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.id");
        params.put("asc", false);
        Page<TbUserEntity> page = new Query<TbUserEntity>(params).getPage();
        return page.setRecords(baseMapper.selectMallUserPage(page, params));
    }

    @Override
    public boolean add(TbUserEntity mallUser) {
        return this.save(mallUser);
    }

    @Override
    public boolean update(TbUserEntity mallUser) {
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
    public TbUserEntity queryByMobile(String mobile) {
        return baseMapper.selectOne(new QueryWrapper<TbUserEntity>().eq("MOBILE", mobile));
    }

    @Override
    public String loginByMobile(String mobile, String password) {
        TbUserEntity user = queryByMobile(mobile);
        AbstractAssert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new BusinessException("手机号或密码错误");
        }

        return user.getId();
    }

    @Override
    public TbUserEntity selectByOpenId(String openId) {
        TbUserEntity userEntity = new TbUserEntity();
        userEntity.setOpenId(openId);
        return baseMapper.selectOne(new QueryWrapper<TbUserEntity>().eq("OPEN_ID", openId));
    }

    @Override
    public TbUserEntity saveOrUpdateByOpenId(WxMpUser userWxInfo) {
        TbUserEntity entity = this.getOne(new QueryWrapper<TbUserEntity>().eq("OPEN_ID", userWxInfo.getOpenId()));
        if (entity == null) {
            entity = new TbUserEntity();
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
