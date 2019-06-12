/*
 * 项目名称:platform-plus
 * 类名称:SysOrgDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-22 11:11:13        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.modules.sys.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 组织机构Dao
 *
 * @author 李鹏军
 * @date 2019-01-22 11:11:13
 */
@Mapper
public interface SysOrgDao extends BaseMapper<SysOrgEntity> {

    /**
     * 查询存在的最大ID
     *
     * @param orgNo 机构编码
     * @return String
     */
    String queryMaxIdByParentNo(String orgNo);

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysOrgEntity> queryAll(Map<String, Object> params);

    /**
     * 根据orgNo查询所有下级列表
     *
     * @param orgNo 机构编码
     * @return List
     */
    List<SysOrgEntity> selectChildrensByOrgNo(String orgNo);
}
