/*
 * 项目名称:tongyi-component
 * 类名称:SysRoleServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.Query;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.SysConstant;
import com.tongyi.modules.sys.dao.SysRoleDao;
import com.tongyi.modules.sys.dao.SysUserDao;
import com.tongyi.modules.sys.entity.SysRoleEntity;
import com.tongyi.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @author 林佛权
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserDao sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleOrgService sysRoleOrgService;

    @Override
    public List<SysRoleEntity> selectListByMap(Map<String, Object> params) {
        return baseMapper.listAll(params);
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
        if (SysConstant.SUPER_ADMIN.equals(role.getCreateUserId())) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new BusinessException("新增角色的权限，已超出你的权限范围");
        }
    }

    @Override
    public SysRoleEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysRoleEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysRoleEntity> listPage(int current, int size, Map<String, Object> params) {
        //排序
        params.put("sidx", "t.create_time");
        params.put("asc", false);
        Page<SysRoleEntity> page = new Query<SysRoleEntity>(current,size,params).getPage();
        List<SysRoleEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysRoleEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        this.checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与机构关系
        sysRoleOrgService.saveOrUpdate(role.getRoleId(), role.getOrgNoList());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        this.checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        //保存角色与机构关系
        sysRoleOrgService.saveOrUpdate(role.getRoleId(), role.getOrgNoList());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] roleIds) {
        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);

        //删除角色与机构关联
        sysRoleOrgService.deleteBatch(roleIds);
        //删除角色
        return this.removeByIds(Arrays.asList(roleIds));
    }
}
