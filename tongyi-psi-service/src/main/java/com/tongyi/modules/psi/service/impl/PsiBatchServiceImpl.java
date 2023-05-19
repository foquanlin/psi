/*
 * 项目名称:项目名称
 * 类名称:PsiBatchServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiBatchDao;
import com.tongyi.modules.psi.entity.PsiBatchEntity;
import com.tongyi.modules.psi.service.PsiBatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 批次Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Service("psiBatchService")
public class PsiBatchServiceImpl extends ServiceImpl<PsiBatchDao, PsiBatchEntity> implements PsiBatchService{

    @Override
    public PsiBatchEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiBatchEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiBatchEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiBatchEntity> page = new Query<PsiBatchEntity>(current,size,params).getPage();
        List<PsiBatchEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiBatchEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiBatchEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiBatchEntity entity) {
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
