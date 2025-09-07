/*
 * 项目名称:tongyi-component
 * 类名称:ScheduleJobServiceImpl.java
 * 包名称:com.tongyi.modules.job.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.job.dao.ScheduleJobDao;
import com.tongyi.modules.job.entity.ScheduleJobEntity;
import com.tongyi.modules.job.service.ScheduleJobService;
import com.tongyi.modules.job.service.ScheduleStatus;
import com.tongyi.modules.job.utils.ScheduleUtils;
import jakarta.annotation.PostConstruct;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.*;

/**
 * @author 林佛权
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobEntity> scheduleJobList = this.list();
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public void updateBatch(String[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("list", Arrays.asList(jobIds));
        map.put("status", status);
        super.baseMapper.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String[] jobIds) {
        for (String jobId : jobIds) {
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(String[] jobIds) {
        for (String jobId : jobIds) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(String[] jobIds) {
        for (String jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }


    @Override
    public ScheduleJobEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<ScheduleJobEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<ScheduleJobEntity> listPage(int current, int size, Map<String, Object> params) {
        Page<ScheduleJobEntity> page = new Query<ScheduleJobEntity>(current,size,params).getPage();
        List<ScheduleJobEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<ScheduleJobEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        boolean saved = super.save(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        return saved;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        return super.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        for (Serializable jobId : ids) {
            ScheduleUtils.deleteScheduleJob(scheduler, (String)jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(ids));
        return true;
    }
}
