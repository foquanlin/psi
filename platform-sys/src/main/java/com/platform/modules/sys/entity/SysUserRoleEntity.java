/*
 * 项目名称:platform-plus
 * 类名称:SysUserRoleEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author 林佛权
 */
@Data
@TableName("SYS_USER_ROLE")
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;
}
