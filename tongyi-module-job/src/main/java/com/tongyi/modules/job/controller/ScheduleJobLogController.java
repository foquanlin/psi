/*
 * 项目名称:tongyi-component
 * 类名称:ScheduleJobLogController.java
 * 包名称:com.tongyi.modules.job.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.job.controller;

import com.tongyi.common.utils.RestResponse;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.job.entity.ScheduleJobLogEntity;
import com.tongyi.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 分页查询定时任务日志
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = scheduleJobLogService.listPage(current, size, params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param logId logId
     * @return RestResponse
     */
    @GetMapping("/info/{logId}")
    public RestResponse info(@PathVariable("logId") String logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return RestResponse.success().put("log", log);
    }
}
