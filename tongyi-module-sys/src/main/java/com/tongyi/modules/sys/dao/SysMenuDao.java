/*
 * 项目名称:tongyi-component
 * 类名称:SysMenuDao.java
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
import com.tongyi.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author 林佛权
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return List
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询所有菜单
     *
     * @return List
     */
    List<SysMenuEntity> queryList();

    /**
     * 查询用户的权限列表
     *
     * @param parentId 父级菜单
     * @return String
     */
    String queryMaxIdByParentId(String parentId);

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysMenuEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysMenuEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
