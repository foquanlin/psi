/*
 * 项目名称:tongyi-component
 * 类名称:SysRoleOrgDao.java
 * 包名称:com.tongyi.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 17:20:07        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.sys.entity.SysRoleOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色与机构对应关系Dao
 *
 * @author 林佛权
 */
@Mapper
public interface SysRoleOrgDao extends BaseMapper<SysRoleOrgEntity> {

    /**
     * 删除根据角色Id
     *
     * @param roleId 角色Id
     * @return int
     */
    int deleteByRoleId(String roleId);

    /**
     * 根据角色ID，获取机构ID列表
     *
     * @param roleId 角色Id
     * @return List
     */
    List<String> queryOrgNoList(String roleId);

    /**
     * 根据用户ID获取权限机构列表
     *
     * @param userId 用户ID
     * @return List
     */
    List<String> queryOrgNoListByUserId(String userId);

    /**
     * offlineBatch
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
    List<SysRoleOrgEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysRoleOrgEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
