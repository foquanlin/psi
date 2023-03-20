/*
 * 项目名称:项目名称
 * 类名称:PsiStockServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiStockDao;
import com.tongyi.modules.psi.entity.PsiAllocationEntity;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.io.Serializable;

/**
 * 库存Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Service("psiStockService")
public class PsiStockServiceImpl extends ServiceImpl<PsiStockDao, PsiStockEntity> implements PsiStockService{

    @Override
    public PsiStockEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiStockEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiStockEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiStockEntity> page = new Query<PsiStockEntity>(current,size,params).getPage();
        List<PsiStockEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiStockEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiStockEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiStockEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        PsiStockEntity entity = this.getById(id);
        if (PsiStockEntity.Catalog.TIAOZHENG != PsiStockEntity.Catalog.valueOf(entity.getCatalog())){
            throw new BusinessException("库存调整数据才可以删除");
        }
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }

    @Override
    public BigDecimal stockNum(String warehouseId, String goodsId) {
        return baseMapper.sumStockByGoods(warehouseId,goodsId);
    }

    @Override
    public BigDecimal stockNum(String warehouseId, String goodsId, String skuId) {
        return baseMapper.sumStockBySku(warehouseId,goodsId,skuId);
    }

    @Override
    public void deleteByOrderId(String orderId) {
        baseMapper.delete(new LambdaQueryWrapper<PsiStockEntity>().eq(PsiStockEntity::getOrderId,orderId));
    }

    @Override
    public void deleteByAllocationGoods(PsiAllocationGoodsEntity item) {
        baseMapper.delete(new LambdaQueryWrapper<PsiStockEntity>()
                .eq(PsiStockEntity::getOrderId,item.getAllocationId())
                .eq(PsiStockEntity::getGoodsId,item.getGoodsId())
                .eq(PsiStockEntity::getSkuId,item.getSkuId()));
    }
}
