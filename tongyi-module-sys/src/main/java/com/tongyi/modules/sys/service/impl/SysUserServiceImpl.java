/*
 * 项目名称:tongyi-component
 * 类名称:SysUserServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysUserDao;
import com.tongyi.modules.sys.entity.SysUserEntity;
import com.tongyi.modules.sys.service.SysRoleService;
import com.tongyi.modules.sys.service.SysUserRoleService;
import com.tongyi.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @author 林佛权
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<String> queryAllMenuId(String userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String userName) {
        return this.getOne(new QueryWrapper<SysUserEntity>().eq("USER_NAME", userName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEntity(SysUserEntity user, Map<String, Object> params) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(SysUserEntity.DEFAULT_PW, salt).toHex());
        user.setSalt(salt);
        this.save(user);

        //检查角色是否越权
        checkRole(user, params);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEntity(SysUserEntity user, Map<String, Object> params) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
        checkRole(user, params);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPw(String[] userIds) {
        for (int i = 0; i < userIds.length; i++) {
            SysUserEntity user = this.getById(userIds[i]);

            user.setPassword(new Sha256Hash(SysUserEntity.DEFAULT_PW, user.getSalt()).toHex());

            this.updateById(user);
        }
    }

    @Override
    public List<SysUserEntity> selectField(String fields) {
        return super.list(new QueryWrapper<SysUserEntity>().select(fields));
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user, Map<String, Object> params) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }

        //查询用户权限下的角色列表
        List<String> roleIdList = sysRoleService.queryRoleIdList(params);

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new BusinessException("新增用户所选角色，不是本人创建");
        }
    }
    @Override
    public SysUserEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysUserEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysUserEntity> listPage(int current, int size, Map<String, Object> params) {
        //排序
        params.put("sidx", "t.create_time");
        params.put("asc", false);
        Page<SysUserEntity> page = new Query<SysUserEntity>(current,size,params).getPage();
        List<SysUserEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysUserEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysUserEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysUserEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }
}
