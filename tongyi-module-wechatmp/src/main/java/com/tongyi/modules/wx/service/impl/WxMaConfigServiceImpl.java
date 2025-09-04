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

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.wx.dao.WxMaConfigDao;
import com.tongyi.modules.wx.entity.WxAccount;
import com.tongyi.modules.wx.entity.WxMaConfigEntity;
import com.tongyi.modules.wx.service.WxMaConfigService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序配置Service实现类
 *
 * @author 林佛权
 * @since 2020-04-05 21:58:47
 */
@Slf4j
@Service("wxMaConfigService")
public class WxMaConfigServiceImpl extends ServiceImpl<WxMaConfigDao, WxMaConfigEntity> implements WxMaConfigService {

    @Autowired
    private WxMaService wxMaService;

    @Override
    public List<WxMaConfigEntity> listAll(Map<String, Object> params) {
        return baseMapper.listAll(params);
    }

    @Override
    public PageInfo<WxMaConfigEntity> queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "t.id");
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


    @PostConstruct
    public void loadWxMaConfigStorages(){
        log.info("加载小程序配置...");
        List<WxMaConfigEntity> list = this.list();
        if (list == null || list.isEmpty()) {
            log.info("未读取到小程序配置，请在管理后台添加");
            return;
        }
        log.info("加载到{}条小程序配置",list.size());
        list.forEach(this::addAccountToRuntime);
        log.info("小程序配置加载完成");
    }

    /**
     * 添加账号到当前程序，如首次添加需初始化configStorageMap
     * @param entity
     */
    private synchronized void addAccountToRuntime(WxMaConfigEntity entity){
        String appid = entity.getAppId();
        WxMaConfig config = entity.newConfig();
        try {
            wxMaService.addConfig(appid,config);
        }catch (NullPointerException e){
            log.info("需初始化configStorageMap...");
            Map<String, WxMaConfig> configStorages = new HashMap<>(4);
            configStorages.put(appid,config);
            wxMaService.setMultiConfigs(configStorages,appid);
        }
    }
}
