/*
 * 项目名称:platform-plus
 * 类名称:Oauth2Token.java
 * 包名称:com.platform.modules.sys.oauth2
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author 林佛权
 */
public class Oauth2Token implements AuthenticationToken {
    private String token;

    public Oauth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
