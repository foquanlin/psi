/*
 * 项目名称:platform-plus
 * 类名称:ScheduleJobLogService.java
 * 包名称:com.platform.modules.job.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.job.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author 李鹏军
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    /**
     * 获取分页数据
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

}
