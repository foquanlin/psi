/*
 *
 *      Copyright (c) 2018-2099, lipengjun All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the fly2you.cn developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lipengjun (939961241@qq.com)
 *
 */
package com.tongyi.modules.wx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.wx.dao.WxMaSessionDao;
import com.tongyi.modules.wx.entity.WxMaSessionEntity;
import com.tongyi.modules.wx.service.WxMaSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author 林佛权
 * @since 2020-11-04 17:52:19
 */
@Service("wxMaSessionService")
public class WxMaSessionServiceImpl extends ServiceImpl<WxMaSessionDao, WxMaSessionEntity> implements WxMaSessionService {

    @Override
    public List<WxMaSessionEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public PageInfo<WxMaSessionEntity> queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "t.id");
        params.put("asc", false);
        Page<WxMaSessionEntity> page = new Query<WxMaSessionEntity>(params).getPage();
        return new PageInfo<WxMaSessionEntity>(page.getCurrent(),page.getSize()).setList(baseMapper.selectWxMaSessionPage(page, params)).setTotal(page.getTotal());
    }

    @Override
    public boolean add(WxMaSessionEntity wxMaSession) {
        return super.save(wxMaSession);
    }

    @Override
    public boolean update(WxMaSessionEntity wxMaSession) {
        return super.updateById(wxMaSession);
    }

    @Override
    public boolean delete(String id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(String[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }

    @Override
    public WxMaSessionEntity findByOpenid(String openid) {
        return baseMapper.selectOne(new QueryWrapper<WxMaSessionEntity>().eq("OPEN_ID",openid));
    }

    @Override
    public boolean save(WxMaSessionEntity entity) {
        return super.save(entity);
    }

    @Override
    public WxMaSessionEntity getById(String id) {
        return super.getById(id);
    }
}
