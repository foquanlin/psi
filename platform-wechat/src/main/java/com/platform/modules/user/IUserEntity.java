/*
 * 项目名称:platform-plus
 * 类名称:UserEntity.java
 * 包名称:com.platform.modules.app.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author 林佛权
 */
public interface IUserEntity{

    public String getId();

    public void setId(String id);

    public String getUserName();

    public void setUserName(String userName);
    public String getPassword();
    public void setPassword(String password);

    public Integer getGender();

    public void setGender(Integer gender);

    public Date getBirthday();
    public void setBirthday(Date birthday);
    public Date getRegisterTime();

    public void setRegisterTime(Date registerTime);

    public Date getLastLoginTime();

    public void setLastLoginTime(Date lastLoginTime);

    public String getLastLoginIp();
    public void setLastLoginIp(String lastLoginIp);

    public String getNickname();

    public void setNickname(String nickname);

    public String getMobile();

    public void setMobile(String mobile);
    public String getRegisterIp();

    public void setRegisterIp(String registerIp);

    public String getHeadImgUrl();

    public void setHeadImgUrl(String headImgUrl);

    public String getAliUserId();
    public void setAliUserId(String aliUserId);
    public String getOpenId();

    public void setOpenId(String openId);
    public String getQqOpenId();
    public void setQqOpenId(String qqOpenId);

    public String getMpOpenId();
    public void setMpOpenId(String mpOpenId);
    public String getUnionId();
    public void setUnionId(String unionId);

    public Integer getSubscribe();

    public void setSubscribe(Integer subscribe);

    public String getSubscribeTime();

    public void setSubscribeTime(String subscribeTime);
}
