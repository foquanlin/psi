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
import com.tongyi.modules.wx.entity.WxMaSessionEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author 林佛权
 * @since 2020-11-04 17:52:19
 */
public interface WxMaSessionService extends IService<WxMaSessionEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<WxMaSessionEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    PageInfo<WxMaSessionEntity> queryPage(Map<String, Object> params);

    /**
     * 新增
     *
     * @param wxMaSession 
     * @return 新增结果
     */
    boolean add(WxMaSessionEntity wxMaSession);

    /**
     * 根据主键更新
     *
     * @param wxMaSession 
     * @return 更新结果
     */
    boolean update(WxMaSessionEntity wxMaSession);

    /**
     * 根据主键删除
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

    WxMaSessionEntity findByOpenid(String openid);

    boolean save(WxMaSessionEntity wxMaSessionEntity);

    WxMaSessionEntity getById(String id);
}
