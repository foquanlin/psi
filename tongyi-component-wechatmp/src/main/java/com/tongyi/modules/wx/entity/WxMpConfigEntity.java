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
package com.tongyi.modules.wx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信公众号配置实体
 *
 * @author 林佛权
 * @since 2020-04-05 21:58:48
 */
@Data
@TableName("wx_mp_config")
public class WxMpConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    private String id;
    /**
     * 微信公众号appId
     */
    private String appId;
    /**
     * 微信公众号secret
     */
    private String secret;
    /**
     * 公众号token
     */
    private String token;
    /**
     * 微信公众号EncodingAESKey
     */
    private String aesKey;
    /**
     * 关注后回复
     */
    private String content;
}
