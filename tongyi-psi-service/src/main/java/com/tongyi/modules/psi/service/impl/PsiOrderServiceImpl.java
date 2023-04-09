/*
 * 项目名称:项目名称
 * 类名称:PsiOrderServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.*;
import com.tongyi.modules.psi.entity.*;
import com.tongyi.modules.psi.service.PsiOrderAmountService;
import com.tongyi.modules.psi.service.PsiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * 采购单Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Service("psiOrderService")
public class PsiOrderServiceImpl extends ServiceImpl<PsiOrderDao, PsiOrderEntity> implements PsiOrderService{
    @Autowired
    private PsiOrderDetailDao orderDetailDao;
    @Autowired
    private PsiOrderAmountDao orderAmountDao;
    @Autowired
    private PsiOrderExpressDao orderExpressDao;
    @Autowired
    private PsiOrderOperationDao orderOperationDao;
    @Autowired
    private PsiOrderInvoiceDao orderInvoiceDao;
    @Autowired
    private PsiStockDao stockDao;
    @Override
    public PsiOrderEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiOrderEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiOrderEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiOrderEntity> page = new Query<PsiOrderEntity>(current,size,params).getPage();
        List<PsiOrderEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiOrderEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiOrderEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiOrderEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        orderDetailDao.delete(new LambdaQueryWrapper<PsiOrderDetailEntity>().eq(PsiOrderDetailEntity::getOrderId,id));
        orderAmountDao.delete(new LambdaQueryWrapper<PsiOrderAmountEntity>().eq(PsiOrderAmountEntity::getOrderId,id));
        orderExpressDao.delete(new LambdaQueryWrapper<PsiOrderExpressEntity>().eq(PsiOrderExpressEntity::getOrderId,id));
        orderOperationDao.delete(new LambdaQueryWrapper<PsiOrderOperationEntity>().eq(PsiOrderOperationEntity::getOrderId,id));
        orderInvoiceDao.delete(new LambdaQueryWrapper<PsiOrderInvoiceEntity>().eq(PsiOrderInvoiceEntity::getOrderId,id));
        stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>().eq(PsiStockEntity::getOrderId,id));
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        Arrays.stream(ids).forEach(id->{
            orderDetailDao.delete(new LambdaQueryWrapper<PsiOrderDetailEntity>().eq(PsiOrderDetailEntity::getOrderId,id));
            orderAmountDao.delete(new LambdaQueryWrapper<PsiOrderAmountEntity>().eq(PsiOrderAmountEntity::getOrderId,id));
            orderExpressDao.delete(new LambdaQueryWrapper<PsiOrderExpressEntity>().eq(PsiOrderExpressEntity::getOrderId,id));
            orderOperationDao.delete(new LambdaQueryWrapper<PsiOrderOperationEntity>().eq(PsiOrderOperationEntity::getOrderId,id));
            orderInvoiceDao.delete(new LambdaQueryWrapper<PsiOrderInvoiceEntity>().eq(PsiOrderInvoiceEntity::getOrderId,id));
            stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>().eq(PsiStockEntity::getOrderId,id));
        });
        return super.removeByIds(Arrays.asList(ids));
    }

    @Override
    public void deleteStock(String[] ids) {
        Arrays.asList(ids).forEach(id-> {
            stockDao.deleteById(id);
        });
    }
}
