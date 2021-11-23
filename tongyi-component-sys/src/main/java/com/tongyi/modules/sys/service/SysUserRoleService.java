/*
 * 项目名称:tongyi-component
 * 类名称:SysUserRoleService.java
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
import com.tongyi.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author 林佛权
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * saveOrUpdate
     *
     * @param userId     用户Id
     * @param roleIdList roleIdList
     */
    void saveOrUpdate(String userId, List<String> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户Id
     * @return List
     */
    List<String> queryRoleIdList(String userId);
}
