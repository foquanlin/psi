/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.JsonObject;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiAllocationDao;
import com.tongyi.modules.psi.dao.PsiAllocationGoodsDao;
import com.tongyi.modules.psi.dao.PsiStockDao;
import com.tongyi.modules.psi.entity.PsiAllocationEntity;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import com.tongyi.modules.psi.service.PsiAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 调拨单Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Service("psiAllocationService")
public class PsiAllocationServiceImpl extends ServiceImpl<PsiAllocationDao, PsiAllocationEntity> implements PsiAllocationService{

    @Autowired
    private PsiAllocationGoodsDao allocationGoodsDao;
    @Autowired
    private PsiStockDao stockDao;
    @Override
    public PsiAllocationEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiAllocationEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiAllocationEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiAllocationEntity> page = new Query<PsiAllocationEntity>(current,size,params).getPage();
        List<PsiAllocationEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiAllocationEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiAllocationEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiAllocationEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>().eq(PsiStockEntity::getOrderId,id));
        allocationGoodsDao.delete(new LambdaQueryWrapper<PsiAllocationGoodsEntity>().eq(PsiAllocationGoodsEntity::getAllocationId,id));
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        Arrays.stream(ids).forEach(id->{
            stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>().eq(PsiStockEntity::getOrderId,id));
            allocationGoodsDao.delete(new LambdaQueryWrapper<PsiAllocationGoodsEntity>().eq(PsiAllocationGoodsEntity::getAllocationId,id));
        });
        return super.removeByIds(Arrays.asList(ids));
    }
}
