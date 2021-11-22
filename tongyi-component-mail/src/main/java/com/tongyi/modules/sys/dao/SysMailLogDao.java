/*
 * 项目名称:tongyi-component
 * 类名称:SysMailLogDao.java
 * 包名称:com.tongyi.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.sys.entity.SysMailLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 邮件发送日志Dao
 *
 * @author 林佛权
 */
@Mapper
public interface SysMailLogDao extends BaseMapper<SysMailLogEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysMailLogEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysMailLogEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
