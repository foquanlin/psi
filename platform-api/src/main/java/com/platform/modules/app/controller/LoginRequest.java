package com.platform.modules.app.controller;

import com.platform.modules.app.entity.FullUserInfo;

import java.io.Serializable;

/**
 * @author:林佛权
 * @Date:Created in 20:53 2020-06-17
 * @Modifyed By:
 */
public class LoginRequest implements Serializable {
    private String mobile;
    private String password;
    private String code;
    private String openId;
    private FullUserInfo userInfo;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public FullUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(FullUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
