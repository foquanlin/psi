/*
 * 项目名称:tongyi-component
 * 类名称:SysRoleOrgEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 17:20:07        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与机构对应关系实体
 * 表名 sys_role_org
 *
 * @author 林佛权
 */
@Data
@TableName("sys_role_org")
public class SysRoleOrgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 机构ID
     */
    private String orgNo;
}
