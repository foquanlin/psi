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
import com.tongyi.common.exception.BusinessException;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


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

    /**
     * 增加订单库存
     * @param stock
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addStock(PsiStockEntity stock) {
        PsiOrderDetailEntity detail = orderDetailDao.selectById(stock.getDetailId());
        BigDecimal num = stockDao.sumStockBySku(null,detail.getId(),null,null,null);
        num = num.abs().add(stock.getNum());
        if (num.compareTo(detail.getNum()) >0){
            throw new BusinessException("请检查订单商品数量,你输入的出入库数量不能大于订单商品数量");
        }
        stockDao.insert(stock);
        this.updateStockStatus(detail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStock(PsiStockEntity stock) {
        PsiOrderDetailEntity detail = orderDetailDao.selectById(stock.getDetailId());
        PsiStockEntity oldStock = stockDao.selectById(stock.getId());
        BigDecimal num = stockDao.sumStockBySku(null,detail.getId(),null,null,null);// 现有库存
        BigDecimal stockNum = num.abs().subtract(oldStock.getNum()).add(stock.getNum());// 减去旧库存记录 + 新库存记录
        if (stockNum.compareTo(detail.getNum()) >0){
            throw new BusinessException("请检查订单商品数量,你输入的出入库数量不能大于订单商品数量");
        }
        stockDao.updateById(stock);
        this.updateStockStatus(detail);
    }

    private void updateStockStatus(PsiOrderDetailEntity detail){
        BigDecimal orderNum = orderDetailDao.sumBySku(detail.getOrderId(),null,null,null,null);
        BigDecimal orderStockNum = stockDao.sumStockBySku(detail.getOrderId(),null,null,null,null);
        PsiOrderEntity order = baseMapper.selectById(detail.getOrderId());
        order.setStockStatus(orderStockNum,orderNum);
        baseMapper.updateById(order);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStock(String id) {
        PsiStockEntity stock = stockDao.selectById(id);
        stockDao.deleteById(id);
        PsiOrderDetailEntity detail = orderDetailDao.selectById(stock.getDetailId());
        this.updateStockStatus(detail);
    }
}
