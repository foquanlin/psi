/*
 * 项目名称:platform-plus
 * 类名称:WechatConfig.java
 * 包名称:com.platform.modules.app.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 16:50    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.app.config;

/**
 * @author 李鹏军
 */
public class WeChatConfig {
    /**
     * 微信公众号APP_ID
     */
    public static final String APP_ID = "";
    /**
     * 微信公众号APP_SECRET
     */
    public static final String APP_SECRET = "";

    public static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
    public static final String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
}
