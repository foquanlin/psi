/*
 * 项目名称:tongyi-component
 * 类名称:ScheduleJobLogServiceImpl.java
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
import com.tongyi.modules.job.dao.ScheduleJobLogDao;
import com.tongyi.modules.job.entity.ScheduleJobLogEntity;
import com.tongyi.modules.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 林佛权
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "t.create_time");
        params.put("asc", false);
        Page<ScheduleJobLogEntity> page = new Query<ScheduleJobLogEntity>(params).getPage();
        return page.setRecords(baseMapper.selectScheduleJobLogPage(page, params));
    }

}
