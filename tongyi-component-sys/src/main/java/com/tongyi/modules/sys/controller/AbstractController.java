/*
 * 项目名称:tongyi-component
 * 类名称:AbstractController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.utils.ShiroUtils;
import com.tongyi.datascope.DataScope;
import com.tongyi.modules.sys.entity.SysUserEntity;
import com.tongyi.modules.sys.service.SysRoleOrgService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller公共组件
 *
 * @author 林佛权
 */
@Slf4j
public abstract class AbstractController {
    protected Logger logger = log;

    @Autowired
    private SysRoleOrgService sysRoleOrgService;

    /**
     * 当前登录用户
     *
     * @return SysUserEntity
     */
    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    /**
     * 当前登录用户ID
     *
     * @return userId
     */
    protected String getUserId() {
        return getUser().getUserId();
    }

    /**
     * 当前登录用户所属机构
     *
     * @return orgNo
     */
    protected String getOrgNo() {
        return getUser().getOrgNo();
    }

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
    protected DataScope getDataScope() {
        DataScope dataScope = new DataScope();
        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
    protected DataScope getDataScope(String userAlias, String orgAlias) {
        DataScope dataScope = new DataScope();
        dataScope.setUserAlias(userAlias);
        dataScope.setOrgAlias(orgAlias);
        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }
}
