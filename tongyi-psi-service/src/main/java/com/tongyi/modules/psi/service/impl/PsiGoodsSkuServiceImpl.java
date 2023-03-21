/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
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
import com.tongyi.modules.psi.dao.*;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.psi.entity.PsiGoodsSpecEntity;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import com.tongyi.modules.psi.service.PsiGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * 商品skuService实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 */
@Service("psiGoodsSkuService")
public class PsiGoodsSkuServiceImpl extends ServiceImpl<PsiGoodsSkuDao, PsiGoodsSkuEntity> implements PsiGoodsSkuService{
    @Autowired
    private PsiGoodsSkuDao goodsSkuDao;
    @Autowired
    private PsiGoodsSpecDao goodsSpecDao;

    @Autowired
    private PsiStockDao stockDao;
    @Autowired
    private PsiAllocationGoodsDao allocationGoodsDao;
    @Autowired
    private PsiOrderDetailDao orderDetailDao;
    @Autowired
    private PsiCheckDetailDao checkDetailDao;
    @Override
    public PsiGoodsSkuEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiGoodsSkuEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiGoodsSkuEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiGoodsSkuEntity> page = new Query<PsiGoodsSkuEntity>(current,size,params).getPage();
        List<PsiGoodsSkuEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiGoodsSkuEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiGoodsSkuEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiGoodsSkuEntity entity) {
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
        Arrays.stream(ids).forEach(id->{
            PsiGoodsSkuEntity sku = baseMapper.selectById(id);
            BigDecimal sum = stockDao.sumStockBySku(null,sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("商品有库存,不能删除!");
            }
            sum = allocationGoodsDao.sumBySku(sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在调拨单商品,不能删除!");
            }
            sum = checkDetailDao.sumBySku(sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在盘点单商品,不能删除!");
            }
            sum = orderDetailDao.sumBySku(PsiOrderEntity.Catalog.BUY.getCode(),PsiOrderEntity.Type.ORDER.getCode(),sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在采购订单商品,不能删除!");
            }
            sum = orderDetailDao.sumBySku(PsiOrderEntity.Catalog.BUY.getCode(),PsiOrderEntity.Type.REFUND.getCode(),sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在采购退单商品,不能删除!");
            }
            sum = orderDetailDao.sumBySku(PsiOrderEntity.Catalog.SALE.getCode(),PsiOrderEntity.Type.ORDER.getCode(),sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在销售订单商品,不能删除!");
            }
            sum = orderDetailDao.sumBySku(PsiOrderEntity.Catalog.SALE.getCode(),PsiOrderEntity.Type.REFUND.getCode(),sku.getGoodsId(),sku.getId());
            if (null!=sum && sum.compareTo(BigDecimal.ZERO)>0){
                throw new BusinessException("存在销售退单商品,不能删除!");
            }
            goodsSkuDao.delete(new LambdaQueryWrapper<PsiGoodsSkuEntity>().eq(PsiGoodsSkuEntity::getGoodsId,id));
            goodsSpecDao.delete(new LambdaQueryWrapper<PsiGoodsSpecEntity>().eq(PsiGoodsSpecEntity::getGoodsId,id));
            super.removeById(id);
        });
        return true;
//        return super.removeByIds(Arrays.asList(ids));
    }
}
