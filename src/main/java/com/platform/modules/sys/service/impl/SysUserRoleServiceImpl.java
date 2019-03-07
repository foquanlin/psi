/*
 * 项目名称:platform-plus
 * 类名称:SysUserRoleServiceImpl.java
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
import com.platform.modules.sys.dao.SysUserRoleDao;
import com.platform.modules.sys.entity.SysUserRoleEntity;
import com.platform.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public void saveOrUpdate(String userId, List<String> roleIdList) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("user_id", userId);
        //先删除用户与角色关系
        this.removeByMap(map);

        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }

        //保存用户与角色关系
        List<SysUserRoleEntity> list = new ArrayList<>(roleIdList.size());
        for (String roleId : roleIdList) {
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.saveBatch(list);
    }

    @Override
    public List<String> queryRoleIdList(String userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(String[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
