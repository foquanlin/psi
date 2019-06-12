/*
 * 项目名称:platform-plus
 * 类名称:SysRoleMenuService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author 李鹏军
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 保存或更新
     *
     * @param roleId     角色ID
     * @param menuIdList menuIdList
     */
    void saveOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId 角色ID
     * @return List
     */
    List<String> queryMenuIdList(String roleId);

    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds roleIds
     * @return int
     */
    int deleteBatch(String[] roleIds);
}
