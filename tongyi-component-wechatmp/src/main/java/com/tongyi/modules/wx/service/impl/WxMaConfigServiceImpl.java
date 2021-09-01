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
import com.tongyi.modules.wx.dao.WxMaConfigDao;
import com.tongyi.modules.wx.entity.WxMaConfigEntity;
import com.tongyi.modules.wx.service.WxMaConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序配置Service实现类
 *
 * @author 林佛权
 * @since 2020-04-05 21:58:47
 */
@Service("wxMaConfigService")
public class WxMaConfigServiceImpl extends ServiceImpl<WxMaConfigDao, WxMaConfigEntity> implements WxMaConfigService {

    @Override
    public List<WxMaConfigEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public PageInfo<WxMaConfigEntity> queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.ID");
        params.put("asc", false);
        Page<WxMaConfigEntity> page = new Query<WxMaConfigEntity>(params).getPage();
        return new PageInfo<WxMaConfigEntity>(page.getCurrent(),page.getSize()).setList(baseMapper.selectWxMaConfigPage(page, params)).setTotal(page.getTotal());
    }

    @Override
    public boolean add(WxMaConfigEntity wxMaConfig) {
        return super.save(wxMaConfig);
    }

    @Override
    public boolean update(WxMaConfigEntity wxMaConfig) {
        return super.updateById(wxMaConfig);
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
    public WxMaConfigEntity getOne() {
        return super.getOne(new QueryWrapper<>(), false);
    }

    @Override
    public WxMaConfigEntity getById(String id) {
        return super.getById(id);
    }
}
