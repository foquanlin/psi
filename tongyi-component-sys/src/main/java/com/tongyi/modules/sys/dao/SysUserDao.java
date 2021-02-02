/*
 * 项目名称:tongyi-component
 * 类名称:SysUserDao.java
 * 包名称:com.tongyi.modules.sys.dao
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tongyi.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 林佛权
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return List
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return List
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysUserEntity> selectListPage(Page<SysUserEntity> page, @Param("params") Map<String, Object> params);

    /**
     * 查询所有
     *
     * @param params 查询参数
     * @return List
     */
    List<SysUserEntity> selectListPage(@Param("params") Map<String, Object> params);
}
