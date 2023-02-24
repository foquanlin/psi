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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体
 *
 * @author 林佛权
 * @since 2020-11-04 17:52:19
 */
@Data
@TableName("wx_ma_session")
public class WxMaSessionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String token;
    /**
     * 
     */
    private String sessionKey;
    /**
     * 
     */
    private String openId;
    /**
     * 
     */
    private String unionId;

    public WxMaSessionEntity() {
    }

    public WxMaSessionEntity(String token,String sessionKey, String openId, String unionId) {
        this.token = token;
        this.sessionKey = sessionKey;
        this.openId = openId;
        this.unionId = unionId;
    }
}
