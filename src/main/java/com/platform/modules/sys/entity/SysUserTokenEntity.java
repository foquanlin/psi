/*
 * 项目名称:platform-plus
 * 类名称:SysUserTokenEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户Token
 *
 * @author 李鹏军
 */
@Data
@TableName("SYS_USER_TOKEN")
public class SysUserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private String userId;
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户名
     */
    @TableField(exist = false)
    private String userName;
}
