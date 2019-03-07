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
package com.platform.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author 李鹏军
 */
@Data
@ApiModel(value = "user对象", description = "用户对象user")
@TableName("TB_USER")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    @ApiModelProperty(hidden = true)
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty(hidden = true)
    private String userName;
    /**
     * 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
     */
    @ApiModelProperty(hidden = true)
    private Integer subscribe;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @ApiModelProperty(hidden = true)
    private String subscribeTime;
    /**
     * 用户的标识
     */
    @ApiModelProperty(hidden = true)
    private String openId;
    /**
     * 微信昵称
     */
    @ApiModelProperty(hidden = true)
    private String nickname;
    /**
     * 用户头像
     */
    @ApiModelProperty(hidden = true)
    private String headImgUrl;
    /**
     * 用户的性别（1是男性，2是女性，0是未知）
     */
    @ApiModelProperty(hidden = true)
    private Integer sex;
    /**
     * 手机号
     */
    @ApiModelProperty(hidden = true)
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(hidden = true)
    private String password;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
}
