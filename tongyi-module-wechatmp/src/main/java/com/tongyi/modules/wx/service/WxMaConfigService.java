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
package com.tongyi.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.wx.entity.WxMaConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 微信小程序配置Service接口
 *
 * @author 林佛权
 * @since 2020-04-05 21:58:47
 */
public interface WxMaConfigService extends IService<WxMaConfigEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<WxMaConfigEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询微信小程序配置
     *
     * @param params 查询参数
     * @return Page
     */
    PageInfo<WxMaConfigEntity> queryPage(Map<String, Object> params);

    /**
     * 新增微信小程序配置
     *
     * @param wxMaConfig 微信小程序配置
     * @return 新增结果
     */
    boolean add(WxMaConfigEntity wxMaConfig);

    /**
     * 根据主键更新微信小程序配置
     *
     * @param wxMaConfig 微信小程序配置
     * @return 更新结果
     */
    boolean update(WxMaConfigEntity wxMaConfig);

    /**
     * 根据主键删除微信小程序配置
     *
     * @param id id
     * @return 删除结果
     */
    boolean delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     * @return 删除结果
     */
    boolean deleteBatch(String[] ids);

    WxMaConfigEntity getOne();

    WxMaConfigEntity getById(String id);
}
