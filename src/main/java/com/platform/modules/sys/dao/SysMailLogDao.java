/*
 * 项目名称:platform-plus
 * 类名称:SysMailLogDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.modules.sys.entity.SysMailLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 邮件发送日志Dao
 *
 * @author 李鹏军
 * @date 2019-03-21 16:46:32
 */
@Mapper
public interface SysMailLogDao extends BaseMapper<SysMailLogEntity> {

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<SysMailLogEntity> selectSysMailLogPage(IPage page, @Param("params") Map<String, Object> params);
}
