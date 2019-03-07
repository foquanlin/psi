/*
 * 项目名称:platform-plus
 * 类名称:SysRoleServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.platform.common.utils.Query;
import com.platform.modules.sys.dao.SysRoleDao;
import com.platform.modules.sys.entity.SysRoleEntity;
import com.platform.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleOrgService sysRoleOrgService;

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.CREATE_TIME");
        params.put("asc", false);
        Page<SysRoleEntity> page = new Query<SysRoleEntity>(params).getPage();
        return page.setRecords(baseMapper.selectSysRolePage(page, params));
    }

    @Override
    public List<SysRoleEntity> selectListByMap(Map<String, Object> params) {
        return baseMapper.selectSysRolePage(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与机构关系
        sysRoleOrgService.saveOrUpdate(role.getRoleId(), role.getOrgNoList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        //保存角色与机构关系
        sysRoleOrgService.saveOrUpdate(role.getRoleId(), role.getOrgNoList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);

        //删除角色与机构关联
        sysRoleOrgService.deleteBatch(roleIds);
    }

    @Override
    public List<String> queryRoleIdList(Map<String, Object> params) {
        return baseMapper.queryRoleIdList(params);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRoleEntity role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (Constant.SUPER_ADMIN.equals(role.getCreateUserId())) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new BusinessException("新增角色的权限，已超出你的权限范围");
        }
    }
}
