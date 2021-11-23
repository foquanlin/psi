/*
 * 项目名称:tongyi-component
 * 类名称:SysRoleService.java
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
import com.tongyi.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author 林佛权
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 查询用户权限下的角色ID列表
     *
     * @param params 查询参数
     * @return List
     */
    List<String> queryRoleIdList(Map<String, Object> params);

    /**
     * selectListByMap
     *
     * @param params 查询参数
     * @return List
     */
    List<SysRoleEntity> selectListByMap(Map<String, Object> params);
}
