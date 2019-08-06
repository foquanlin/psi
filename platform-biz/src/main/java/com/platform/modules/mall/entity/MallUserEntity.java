/*
 * 项目名称:platform-plus
 * 类名称:UserEntity.java
 * 包名称:com.platform.modules.app.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author 李鹏军
 */
@Data
@TableName("MALL_USER")
public class MallUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(hidden = true)
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(hidden = true)
    private String password;
    /**
     * 用户的性别（1是男性，2是女性，0是未知）
     */
    @ApiModelProperty(hidden = true)
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty(hidden = true)
    private Date birthday;
    /**
     * 注册时间
     */
    @ApiModelProperty(hidden = true)
    private Date registerTime;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(hidden = true)
    private Date lastLoginTime;
    /**
     * 最后登录IP
     */
    @ApiModelProperty(hidden = true)
    private String lastLoginIp;
    /**
     * 微信昵称
     */
    @ApiModelProperty(hidden = true)
    private String nickname;
    /**
     * 手机号
     */
    @ApiModelProperty(hidden = true)
    private String mobile;
    /**
     * 注册ip
     */
    @ApiModelProperty(hidden = true)
    private String registerIp;
    /**
     * 用户头像
     */
    @ApiModelProperty(hidden = true)
    private String headImgUrl;
    /**
     * 支付宝用户标识
     */
    @ApiModelProperty(hidden = true)
    private String aliUserId;
    /**
     * 微信小程序用户的标识
     */
    @ApiModelProperty(hidden = true)
    private String openId;
    /**
     * QQ小程序用户的标识
     */
    @ApiModelProperty(hidden = true)
    private String qqOpenId;
    /**
     * 公众号用户的标识
     */
    @ApiModelProperty(hidden = true)
    private String mpOpenId;
    /**
     * 需要用户将公众号、小程序绑定到微信开放平台帐号
     * 同一主体下的unionId一致
     */
    @ApiModelProperty(hidden = true)
    private String unionId;
    /**
     * 公众号关注状态（1是关注，0是未关注），未关注时获取不到其余信息
     */
    @ApiModelProperty(hidden = true)
    private Integer subscribe;
    /**
     * 用户关注公众号时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @ApiModelProperty(hidden = true)
    private String subscribeTime;
}
