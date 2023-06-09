/*
 * 项目名称:tongyi-component
 * 类名称:ScheduleJobDao.java
 * 包名称:com.tongyi.modules.job.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-22 10:13:48        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.job.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 定时任务Dao
 *
 * @author 林佛权
 */
@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     *
     * @param map map
     * @return int
     */
    int updateBatch(Map<String, Object> map);


    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<ScheduleJobEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<ScheduleJobEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
