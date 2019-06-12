/*
 * 项目名称:platform-plus
 * 类名称:SysRoleMenuServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.modules.sys.dao.SysRoleMenuDao;
import com.platform.modules.sys.entity.SysRoleMenuEntity;
import com.platform.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author 李鹏军
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(String roleId, List<String> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new String[]{roleId});

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        List<SysRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
        for (String menuId : menuIdList) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.saveBatch(list);
    }

    @Override
    public List<String> queryMenuIdList(String roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(String[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }

}
