/*
 * 项目名称:platform-plus
 * 类名称:ScheduleJobLogDao.java
 * 包名称:com.platform.modules.job.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-22 10:13:48        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志Dao
 *
 * @author 李鹏军
 * @date 2019-01-22 10:13:48
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<ScheduleJobLogEntity> selectScheduleJobLogPage(IPage page, @Param("params") Map<String, Object> params);
}
