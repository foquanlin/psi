/*
 * 项目名称:platform-plus
 * 类名称:SysMenuServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.utils.Constant;
import com.platform.common.utils.StringUtils;
import com.platform.modules.sys.dao.SysMenuDao;
import com.platform.modules.sys.entity.SysMenuEntity;
import com.platform.modules.sys.service.SysMenuService;
import com.platform.modules.sys.service.SysRoleMenuService;
import com.platform.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(String parentId) {
        return baseMapper.selectList(new QueryWrapper<SysMenuEntity>().eq("PARENT_ID", parentId).orderByAsc("ORDER_NUM"));
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN.equals(userId)) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public boolean delete(String menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        Map<String, Object> map = new HashMap<>(2);
        map.put("menu_id", menuId);
        return sysRoleMenuService.removeByMap(map);
    }

    @Override
    public List<SysMenuEntity> queryList() {
        return baseMapper.queryList();
    }

    @Override
    public boolean add(SysMenuEntity menu) {
        String parentId = menu.getParentId();
        String maxId = baseMapper.queryMaxIdByParentId(parentId);

        menu.setMenuId(StringUtils.addOne(parentId, maxId));
        return this.save(menu);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<String> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<String> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<>();

        for (SysMenuEntity entity : menuList) {
            //目录
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
