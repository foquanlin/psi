/*
 * 项目名称:tongyi-component
 * 类名称:ScheduleJobController.java
 * 包名称:com.tongyi.modules.job.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.job.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.common.validator.ValidatorUtils;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.job.entity.ScheduleJobEntity;
import com.tongyi.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 分页查询定时任务
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = scheduleJobService.listPage(current, size,params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param jobId 主键
     * @return RestResponse
     */
    @GetMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public RestResponse info(@PathVariable("jobId") String jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);

        return RestResponse.success().put("schedule", schedule);
    }

    /**
     * 新增定时任务
     *
     * @param scheduleJob scheduleJob
     * @return RestResponse
     */
    @SysLog("新增定时任务")
    @PostMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public RestResponse save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.addEntity(scheduleJob);

        return RestResponse.success();
    }

    /**
     * 修改定时任务
     *
     * @param scheduleJob scheduleJob
     * @return RestResponse
     */
    @SysLog("修改定时任务")
    @PostMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public RestResponse update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.updateEntity(scheduleJob);

        return RestResponse.success();
    }

    /**
     * 删除定时任务
     *
     * @param jobIds jobIds
     * @return RestResponse
     */
    @SysLog("删除定时任务")
    @PostMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public RestResponse delete(@RequestBody String[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return RestResponse.success();
    }

    /**
     * 立即执行任务
     *
     * @param jobIds jobIds
     * @return RestResponse
     */
    @SysLog("立即执行任务")
    @PostMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public RestResponse run(@RequestBody String[] jobIds) {
        scheduleJobService.run(jobIds);

        return RestResponse.success();
    }

    /**
     * 暂停定时任务
     *
     * @param jobIds jobIds
     * @return RestResponse
     */
    @SysLog("暂停定时任务")
    @PostMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public RestResponse pause(@RequestBody String[] jobIds) {
        scheduleJobService.pause(jobIds);

        return RestResponse.success();
    }

    /**
     * 恢复定时任务
     *
     * @param jobIds jobIds
     * @return RestResponse
     */
    @SysLog("恢复定时任务")
    @PostMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public RestResponse resume(@RequestBody String[] jobIds) {
        scheduleJobService.resume(jobIds);

        return RestResponse.success();
    }
}
