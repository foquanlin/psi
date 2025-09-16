/*
 * 项目名称:tongyi-component
 * 类名称:SysOrgEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 11:29:22        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tongyi.common.validator.group.AddGroup;
import com.tongyi.common.validator.group.UpdateGroup;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构实体
 * 表名 sys_org
 *
 * @author 林佛权
 */
@Data
@TableName("sys_org")
public class SysOrgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 机构编码
     */
    @TableId
    private String orgNo;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orgName;
    /**
     * 上级机构ID，一级机构为0
     */
    @NotBlank(message = "上级机构不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String parentNo;
    /**
     * 级别
     */
    @NotNull(message = "机构级别不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer orgType;
    /**
     * 状态  0：无效   1：有效
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建者ID
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private String parentName;
}
