/*
 * 项目名称:tongyi-component
 * 类名称:SysRoleMenuDao.java
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 *
 * @author 林佛权
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId 角色Id
     * @return List
     */
    List<String> queryMenuIdList(String roleId);

    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds 角色Ids
     * @return int
     */
    int deleteBatch(String[] roleIds);

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysRoleMenuEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysRoleMenuEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
