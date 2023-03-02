/*
 * 项目名称:tongyi-component
 * 类名称:SysLogServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysLogDao;
import com.tongyi.modules.sys.entity.SysLogEntity;
import com.tongyi.modules.sys.service.SysLogService;
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
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public SysLogEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysLogEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysLogEntity> listPage(int current, int size, Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<SysLogEntity> page = new Query<SysLogEntity>(params).getPage();
        page = super.baseMapper.selectPage(page,
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "USER_NAME", key)
                        .or().like(StringUtils.isNotBlank(key), "OPERATION", key)
                        .orderByDesc("CREATE_TIME"));
        return new PageInfo<SysLogEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(page.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysLogEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysLogEntity entity) {
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
