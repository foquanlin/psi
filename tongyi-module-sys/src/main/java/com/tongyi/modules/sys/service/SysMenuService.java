/*
 * 项目名称:tongyi-component
 * 类名称:SysMenuService.java
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
import com.tongyi.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author 林佛权
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父级菜单ID
     * @param menuIdList menuIdList
     * @return List
     */
    List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父级菜单ID
     * @return List
     */
    List<SysMenuEntity> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return List
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     *
     * @param userId 用户Id
     * @return List
     */
    List<SysMenuEntity> getUserMenuList(String userId);

    /**
     * 查询所有菜单
     *
     * @return List
     */
    List<SysMenuEntity> queryList();
}
