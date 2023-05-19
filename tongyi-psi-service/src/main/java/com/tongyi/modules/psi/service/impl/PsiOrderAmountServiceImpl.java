/*
 * 项目名称:项目名称
 * 类名称:PsiOrderAmountServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiOrderAmountDao;
import com.tongyi.modules.psi.dao.PsiOrderDao;
import com.tongyi.modules.psi.entity.PsiOrderAmountEntity;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import com.tongyi.modules.psi.service.PsiOrderAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 订单账目Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Service("psiOrderAmountService")
public class PsiOrderAmountServiceImpl extends ServiceImpl<PsiOrderAmountDao, PsiOrderAmountEntity> implements PsiOrderAmountService{

    @Autowired
    private PsiOrderDao orderDao;
    @Override
    public PsiOrderAmountEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiOrderAmountEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiOrderAmountEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiOrderAmountEntity> page = new Query<PsiOrderAmountEntity>(current,size,params).getPage();
        List<PsiOrderAmountEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiOrderAmountEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiOrderAmountEntity entity) {
        PsiOrderEntity order = orderDao.selectById(entity.getOrderId());
        BigDecimal payedAmount = baseMapper.sumByOrderId(order.getId());
        BigDecimal total = payedAmount.add(entity.getAmount());
        if (total.compareTo(order.getOrderAmount())>0){
            throw new BusinessException("已支付金额不能大于订单金额");
        }
        boolean added = super.save(entity);
        order.setPayStatus(total);
        orderDao.updateById(order);
        return added;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiOrderAmountEntity entity) {
        PsiOrderEntity order = orderDao.selectById(entity.getOrderId());
        PsiOrderAmountEntity oldAmount = baseMapper.selectById(entity.getId());
        BigDecimal payedAmount = baseMapper.sumByOrderId(order.getId());
        BigDecimal total = payedAmount.subtract(oldAmount.getAmount()).add(entity.getAmount());
        if (total.compareTo(order.getOrderAmount())>0){
            throw new BusinessException("已支付金额不能大于订单金额");
        }
        boolean updated = super.updateById(entity);
        order.setPayStatus(total);
        orderDao.updateById(order);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        PsiOrderAmountEntity entity = baseMapper.selectById(id);
        boolean delted = super.removeById(id);
        PsiOrderEntity order = orderDao.selectById(entity.getOrderId());
        BigDecimal payedAmount = baseMapper.sumByOrderId(order.getId());
        order.setPayStatus(payedAmount);
        orderDao.updateById(order);
        return delted;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        throw new UnsupportedOperationException();
//        return super.removeByIds(Arrays.asList(ids));
    }
}
