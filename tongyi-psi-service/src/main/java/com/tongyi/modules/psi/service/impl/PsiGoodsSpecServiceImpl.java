/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSpecServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiGoodsSpecDao;
import com.tongyi.modules.psi.entity.PsiGoodsSpecEntity;
import com.tongyi.modules.psi.service.PsiGoodsSpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 */
@Service("psiGoodsSpecService")
public class PsiGoodsSpecServiceImpl extends ServiceImpl<PsiGoodsSpecDao, PsiGoodsSpecEntity> implements PsiGoodsSpecService{

    @Override
    public PsiGoodsSpecEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiGoodsSpecEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiGoodsSpecEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiGoodsSpecEntity> page = new Query<PsiGoodsSpecEntity>(current,size,params).getPage();
        List<PsiGoodsSpecEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiGoodsSpecEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    public void execute(Serializable id, Map<String, Object> params, ModuleExecute<PsiGoodsSpecEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fun);
        PsiGoodsSpecEntity entity = this.getById(id);
        this.execute(entity,params,fun);
    }

    @Override
    public void execute(PsiGoodsSpecEntity entity, Map<String, Object> params, ModuleExecute<PsiGoodsSpecEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(fun);
        fun.apply(entity,params);
    }

    @Override
    public void execute(PsiGoodsSpecEntity entity, Map<String, Object> params, ModuleExecute<PsiGoodsSpecEntity, Map<String, Object>, Void>... funs) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(funs);
        Arrays.stream(funs).forEach(fun->{
            fun.apply(entity,params);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiGoodsSpecEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiGoodsSpecEntity entity) {
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
