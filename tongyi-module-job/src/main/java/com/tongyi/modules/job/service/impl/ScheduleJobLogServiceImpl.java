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
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.job.dao.ScheduleJobLogDao;
import com.tongyi.modules.job.entity.ScheduleJobLogEntity;
import com.tongyi.modules.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 林佛权
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {
    @Override
    public ScheduleJobLogEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<ScheduleJobLogEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<ScheduleJobLogEntity> listPage(int current, int size, Map<String, Object> params) {
        Page<ScheduleJobLogEntity> page = new Query<ScheduleJobLogEntity>(current,size,params).getPage();
        List<ScheduleJobLogEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<ScheduleJobLogEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    public void execute(Serializable id, Map<String, Object> params, ModuleExecute<ScheduleJobLogEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fun);
        ScheduleJobLogEntity entity = this.getById(id);
        this.execute(entity,params,fun);
    }

    @Override
    public void execute(ScheduleJobLogEntity entity, Map<String, Object> params, ModuleExecute<ScheduleJobLogEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(fun);
        fun.apply(entity,params);
    }

    @Override
    public void execute(ScheduleJobLogEntity entity, Map<String, Object> params, ModuleExecute<ScheduleJobLogEntity, Map<String, Object>, Void>... funs) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(funs);
        Arrays.stream(funs).forEach(fun->{
            fun.apply(entity,params);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(ScheduleJobLogEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(ScheduleJobLogEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }

}
