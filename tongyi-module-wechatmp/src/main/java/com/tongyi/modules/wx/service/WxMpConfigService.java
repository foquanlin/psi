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
import com.tongyi.modules.wx.entity.WxMpConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 微信公众号配置Service接口
 *
 * @author 林佛权
 * @since 2020-04-05 21:58:48
 */
public interface WxMpConfigService extends IService<WxMpConfigEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<WxMpConfigEntity> listAll(Map<String, Object> params);

    /**
     * 分页查询微信公众号配置
     *
     * @param params 查询参数
     * @return Page
     */
    PageInfo<WxMpConfigEntity> queryPage(Map<String, Object> params);

    /**
     * 新增微信公众号配置
     *
     * @param wxMpConfig 微信公众号配置
     * @return 新增结果
     */
    boolean add(WxMpConfigEntity wxMpConfig);

    /**
     * 根据主键更新微信公众号配置
     *
     * @param wxMpConfig 微信公众号配置
     * @return 更新结果
     */
    boolean update(WxMpConfigEntity wxMpConfig);

    /**
     * 根据主键删除微信公众号配置
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

    WxMpConfigEntity getOne(String appId);

    WxMpConfigEntity getOne();

    WxMpConfigEntity getById(String id);
}
