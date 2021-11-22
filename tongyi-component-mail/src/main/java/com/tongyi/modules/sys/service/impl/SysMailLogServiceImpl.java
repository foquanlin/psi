/*
 * 项目名称:tongyi-component
 * 类名称:SysMailLogServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysMailLogDao;
import com.tongyi.modules.sys.entity.SysMailLogEntity;
import com.tongyi.modules.sys.service.SysMailLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送日志Service实现类
 *
 * @author 林佛权
 */
@Service("sysMailLogService")
public class SysMailLogServiceImpl extends ServiceImpl<SysMailLogDao, SysMailLogEntity> implements SysMailLogService {

    @Override
    public SysMailLogEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysMailLogEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysMailLogEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<SysMailLogEntity> page = new Query<SysMailLogEntity>(current,size,params).getPage();
        List<SysMailLogEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysMailLogEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysMailLogEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysMailLogEntity entity) {
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
