/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;

import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiGoodsDao;
import com.tongyi.modules.psi.dao.PsiGoodsSkuDao;
import com.tongyi.modules.psi.dao.PsiGoodsSpecDao;
import com.tongyi.modules.psi.entity.PsiGoodsEntity;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.psi.entity.PsiGoodsSpecEntity;
import com.tongyi.modules.psi.service.PsiGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * 商品Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Service("psiGoodsService")
public class PsiGoodsServiceImpl extends ServiceImpl<PsiGoodsDao, PsiGoodsEntity> implements PsiGoodsService{
    @Autowired
    private PsiGoodsSkuDao goodsSkuDao;
    @Autowired
    private PsiGoodsSpecDao goodsSpecDao;
    @Override
    public PsiGoodsEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiGoodsEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiGoodsEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiGoodsEntity> page = new Query<PsiGoodsEntity>(current,size,params).getPage();
        List<PsiGoodsEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiGoodsEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    public void execute(Serializable id, Map<String, Object> params, ModuleExecute<PsiGoodsEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(fun);
        PsiGoodsEntity entity = this.getById(id);
        this.execute(entity,params,fun);
    }

    @Override
    public void execute(PsiGoodsEntity entity, Map<String, Object> params, ModuleExecute<PsiGoodsEntity, Map<String, Object>, Void> fun) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(fun);
        fun.apply(entity,params);
    }

    @Override
    public void execute(PsiGoodsEntity entity, Map<String, Object> params, ModuleExecute<PsiGoodsEntity, Map<String, Object>, Void>... funs) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(funs);
        Arrays.stream(funs).forEach(fun->{
            fun.apply(entity,params);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiGoodsEntity entity) {
        boolean added = super.save(entity);
        if (added) {
            List<PsiGoodsSpecEntity> newsSpecList = entity.getSpecList();
            List<PsiGoodsSkuEntity> newsSkuList = entity.getSkuList();
            newsSpecList.forEach(item->{
                item.setGoodsId(entity.getId());
                goodsSpecDao.insert(item);
            });
            newsSkuList.forEach(item->{
                item.setGoodsId(entity.getId());
                goodsSkuDao.insert(item);
            });
//            List<PsiGoodsSpecEntity> oldSpecList = goodsSpecDao.selectByGoodsId(entity.getId());
//            List<PsiGoodsSkuEntity> oldSkuList = goodsSkuDao.selectByGoodsId(entity.getId());
        }
        return added;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiGoodsEntity entity) {
        //应该是没用的代码
        // PsiGoodsEntity newsItem = this.getById(entity.getId());
//        List<PsiGoodsSpecEntity> newsSpecList = entity.getSpecList();
//        List<PsiGoodsSkuEntity> newsSkuList = entity.getSkuList();
//        newsSpecList.forEach(item->{
//            item.setGoodsId(entity.getId());
//            if (StringUtils.isBlank(item.getId())) {
//                goodsSpecDao.insert(item);
//            }else {
//                goodsSpecDao.updateById(item);
//            }
//        });
//        newsSkuList.forEach(item->{
//            item.setGoodsId(entity.getId());
//            if (StringUtils.isBlank(item.getId())) {
//                goodsSkuDao.insert(item);
//            }else{
//                goodsSkuDao.updateById(item);
//            }
//        });
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
