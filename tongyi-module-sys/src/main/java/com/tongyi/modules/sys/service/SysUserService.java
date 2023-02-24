/*
 * 项目名称:tongyi-component
 * 类名称:SysUserService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 林佛权
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户Id
     * @return List
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 根据用户名，查询系统用户
     *
     * @param userName 用户名
     * @return SysUserEntity
     */
    SysUserEntity queryByUserName(String userName);

    /**
     * 保存用户
     *
     * @param user   用户
     * @param params 查询参数
     */
    void addEntity(SysUserEntity user, Map<String, Object> params);

    /**
     * 修改用户
     *
     * @param user   用户
     * @param params 查询参数
     */
    void updateEntity(SysUserEntity user, Map<String, Object> params);

    /**
     * 修改密码
     *
     * @param userId      用户Id
     * @param password    原密码
     * @param newPassword 新密码
     * @return boolean
     */
    boolean updatePassword(String userId, String password, String newPassword);

    /**
     * 重置密码
     *
     * @param userIds 用户Ids
     */
    void resetPw(String[] userIds);

    List<SysUserEntity> selectField(String fields);
}
