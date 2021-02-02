/*
 * 项目名称:tongyi-component
 * 类名称:ShiroService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.modules.sys.entity.SysUserEntity;
import com.tongyi.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author 林佛权
 */
public interface ShiroService extends IService<SysUserTokenEntity> {
    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @return Set
     */
    Set<String> getUserPermissions(String userId);

    /**
     * 根据token获取用户
     *
     * @param token token
     * @return SysUserEntity
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId 用户ID
     * @return SysUserEntity
     */
    SysUserEntity queryUser(String userId);
}
