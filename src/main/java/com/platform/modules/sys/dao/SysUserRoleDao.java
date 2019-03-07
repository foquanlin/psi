/*
 * 项目名称:platform-plus
 * 类名称:SysUserRoleDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author 李鹏军
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户ID
     * @return List
     */
    List<String> queryRoleIdList(String userId);


    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds 角色ids
     * @return int
     */
    int deleteBatch(String[] roleIds);
}
