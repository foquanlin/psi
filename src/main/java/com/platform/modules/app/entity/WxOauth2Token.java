/*
 * 项目名称:platform-plus
 * 类名称:WeixinOauth2Token.java
 * 包名称:com.platform.modules.app.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 16:23    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.app.entity;

import lombok.Data;

/**
 * @author 李鹏军
 */
@Data
public class WxOauth2Token {
    /**
     * 网页授权接口调用凭证
     */
    private String accessToken;
    /**
     * 凭证有效时长
     */
    private int expiresIn;
    /**
     * 用于刷新凭证
     */
    private String refreshToken;
    /**
     * 用户标识
     */
    private String openId;
    /**
     * 用户授权作用域
     */
    private String scope;
}
