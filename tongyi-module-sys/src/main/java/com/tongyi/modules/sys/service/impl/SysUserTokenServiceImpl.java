/*
 * 项目名称:tongyi-component
 * 类名称:SysUserTokenServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.Constant;
import com.tongyi.common.utils.Query;
import com.tongyi.common.utils.TokenGenerator;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysUserTokenDao;
import com.tongyi.modules.sys.entity.SysUserTokenEntity;
import com.tongyi.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 林佛权
 */
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    @Override
    public String createToken(String userId) {
        //生成一个token
        String token = null;
        try {
            token = TokenGenerator.generateValue();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + Constant.EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        return token;
    }

    @Override
    public void logout(String userId) {
        //生成一个token
        String token = null;
        try {
            token = TokenGenerator.generateValue();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void offlineBatch(String[] userIds) {
        this.removeByIds(Arrays.asList(userIds));
    }

    @Override
    public SysUserTokenEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysUserTokenEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysUserTokenEntity> listPage(int current, int size, Map<String, Object> params) {
        params.put("nowDate", new Date());
        Page<SysUserTokenEntity> page = new Query<SysUserTokenEntity>(current,size,params).getPage();
        List<SysUserTokenEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysUserTokenEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysUserTokenEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysUserTokenEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }
}
