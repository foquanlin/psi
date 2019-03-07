/*
 * 项目名称:platform-plus
 * 类名称:ScheduleJob.java
 * 包名称:com.platform.modules.job.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.job.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.platform.common.utils.SpringContextUtils;
import com.platform.modules.job.entity.ScheduleJobEntity;
import com.platform.modules.job.entity.ScheduleJobLogEntity;
import com.platform.modules.job.service.ScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 定时任务
 *
 * @author 李鹏军
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

    private ExecutorService service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();

        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY), scheduleJob);
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLogEntity logEntity = new ScheduleJobLogEntity();
        logEntity.setJobId(scheduleJob.getJobId());
        logEntity.setBeanName(scheduleJob.getBeanName());
        logEntity.setMethodName(scheduleJob.getMethodName());
        logEntity.setParams(scheduleJob.getParams());
        logEntity.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            logEntity.setTimes((int) times);
            //任务状态    0：成功    1：失败
            logEntity.setStatus(0);

            log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            logEntity.setTimes((int) times);

            //任务状态    0：成功    1：失败
            logEntity.setStatus(1);
            logEntity.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(logEntity);
        }
    }
}
