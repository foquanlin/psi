/*
 * 项目名称:platform-plus
 * 类名称:SysRoleOrgEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 17:20:07        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与机构对应关系实体
 * 表名 sys_role_org
 *
 * @author 李鹏军
 * @date 2019-01-21 17:20:07
 */
@Data
@TableName("SYS_ROLE_ORG")
public class SysRoleOrgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
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
