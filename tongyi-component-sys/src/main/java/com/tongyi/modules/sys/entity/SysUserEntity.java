/*
 * 项目名称:tongyi-component
 * 类名称:SysUserEntity.java
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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author 林佛权
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 用户性别
     */
    @NotNull(message = "性别不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sex;

    /**
     * 用户真实姓名
     */
    @NotBlank(message = "真实姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String realName;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    /**
     * 机构编码
     */
    @NotBlank(message = "所属机构不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orgNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱服务器地址
     */
    private String emailHost;
    /**
     * 邮箱端口
     */
    private int emailPort;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 邮箱密码
     */
    private String emailPw;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    @TableField(exist = false)
    private List<String> roleIdList;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建者所属机构
     */
    private String createUserOrgNo;

    /**
     * 创建时间
     */
    private Date createTime;
}
