/*
 * 项目名称:tongyi-component
 * 类名称:SysMenuEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tongyi.common.validator.group.AddGroup;
import com.tongyi.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 *
 * @author 林佛权
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId
    private String menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @NotBlank(message = "上级菜单不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String parentId;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    private Integer shows;

    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<?> list;
}
