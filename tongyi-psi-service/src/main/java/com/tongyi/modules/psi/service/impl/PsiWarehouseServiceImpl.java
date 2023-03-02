/*
 * 项目名称:项目名称
 * 类名称:PsiWarehouseServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiWarehouseDao;
import com.tongyi.modules.psi.entity.PsiWarehouseEntity;
import com.tongyi.modules.psi.service.PsiWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * 仓库Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Service("psiWarehouseService")
public class PsiWarehouseServiceImpl extends ServiceImpl<PsiWarehouseDao, PsiWarehouseEntity> implements PsiWarehouseService{

    @Override
    public PsiWarehouseEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiWarehouseEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiWarehouseEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiWarehouseEntity> page = new Query<PsiWarehouseEntity>(current,size,params).getPage();
        List<PsiWarehouseEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiWarehouseEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    public void execute(Serializable id, Map<String, Object> params, ModuleExecute<PsiWarehouseEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fun);
        PsiWarehouseEntity entity = this.getById(id);
        this.execute(entity,params,fun);
    }

    @Override
    public void execute(PsiWarehouseEntity entity, Map<String, Object> params, ModuleExecute<PsiWarehouseEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(fun);
        fun.apply(entity,params);
    }

    @Override
    public void execute(PsiWarehouseEntity entity, Map<String, Object> params, ModuleExecute<PsiWarehouseEntity, Map<String, Object>, Void>... funs) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(funs);
        Arrays.stream(funs).forEach(fun->{
            fun.apply(entity,params);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiWarehouseEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiWarehouseEntity entity) {
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

    @Override
    public void defaultWarehouse(String id) {
        baseMapper.defaultWarehouse(id);
    }

    @Override
    public void warehouseStatus(String id, String status) {
        PsiWarehouseEntity item = baseMapper.selectById(id);
        item.setStatus(status);
        baseMapper.updateById(item);
    }
}
