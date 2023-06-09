/*
 * 项目名称:tongyi-component
 * 类名称:SysUserController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.common.validator.AbstractAssert;
import com.tongyi.common.validator.ValidatorUtils;
import com.tongyi.common.validator.group.AddGroup;
import com.tongyi.common.validator.group.UpdateGroup;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.SysConstant;
import com.tongyi.modules.sys.entity.SysUserEntity;
import com.tongyi.modules.sys.form.PasswordForm;
import com.tongyi.modules.sys.service.SysUserRoleService;
import com.tongyi.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions(value = {"sys:dict:list","sys:user:list"},logical = Logical.OR)
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<SysUserEntity> list = sysUserService.listAll(params);
        list.forEach(item->{
            item.cleanInfo();
        });
        return RestResponse.success().put("list", list);
    }

    /**
     * 所有用户列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {

        //如需数据权限，在参数中添加DataScope
        params.put("dataScope", getDataScope());

        PageInfo page = sysUserService.listPage(current,size,params);
        List<SysUserEntity> list = page.getList();
        list.forEach(item->{
            item.cleanInfo();
        });
        return RestResponse.success().put("page", page);
    }

    /**
     * 获取登录的用户信息
     *
     * @return RestResponse
     */
    @GetMapping("/info")
    public RestResponse info() {
        SysUserEntity user = sysUserService.getById(getUser().getUserId());
        user.cleanInfo();
        return RestResponse.success().put("user", user);
    }

    /**
     * 修改登录用户密码
     *
     * @param form form
     * @return RestResponse
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public RestResponse password(@RequestBody PasswordForm form) {
        AbstractAssert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return RestResponse.error("原密码不正确");
        }

        return RestResponse.success();
    }

    /**
     * 根据主键查询详情
     *
     * @param userId 主键
     * @return RestResponse
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public RestResponse info(@PathVariable("userId") String userId) {
        SysUserEntity user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return RestResponse.success().put("user", user);
    }

    /**
     * 保存用户
     *
     * @param user user
     * @return RestResponse
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public RestResponse save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        Map<String, Object> params = new HashMap<>(2);
        params.put("dataScope", getDataScope());

        user.setCreateUserId(getUserId());
        user.setCreateUserOrgNo(getOrgNo());
        sysUserService.addEntity(user, params);

        return RestResponse.success();
    }

    /**
     * 修改用户
     *
     * @param user user
     * @return RestResponse
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public RestResponse update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        Map<String, Object> params = new HashMap<>(2);
        params.put("dataScope", getDataScope());

        user.setCreateUserId(getUserId());
        user.setCreateUserOrgNo(getOrgNo());
        sysUserService.updateEntity(user, params);

        return RestResponse.success();
    }

    /**
     * 删除用户
     *
     * @param userIds userIds
     * @return RestResponse
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public RestResponse delete(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, SysConstant.SUPER_ADMIN)) {
            return RestResponse.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return RestResponse.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return RestResponse.success();
    }

    /**
     * 重置密码
     *
     * @param userIds userIds
     * @return RestResponse
     */
    @SysLog("重置密码")
    @PostMapping("/resetPw")
    @RequiresPermissions("sys:user:resetPw")
    public RestResponse resetPw(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, SysConstant.SUPER_ADMIN)) {
            return RestResponse.error("系统管理员不能重置");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return RestResponse.error("当前用户不能重置");
        }

        sysUserService.resetPw(userIds);

        return RestResponse.success();
    }
}
