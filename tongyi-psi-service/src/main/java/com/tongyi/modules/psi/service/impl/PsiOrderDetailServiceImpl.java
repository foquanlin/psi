/*
 * 项目名称:项目名称
 * 类名称:PsiOrderDetailServiceImpl.java
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
import com.tongyi.modules.psi.dao.PsiOrderDao;
import com.tongyi.modules.psi.dao.PsiOrderDetailDao;
import com.tongyi.modules.psi.dao.PsiStockDao;
import com.tongyi.modules.psi.entity.PsiOrderDetailEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 订单明细Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Service("psiOrderDetailService")
public class PsiOrderDetailServiceImpl extends ServiceImpl<PsiOrderDetailDao, PsiOrderDetailEntity> implements PsiOrderDetailService{

    @Autowired
    private PsiOrderDao orderDao;
    @Autowired
    private PsiStockDao stockDao;

    @Override
    public PsiOrderDetailEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiOrderDetailEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiOrderDetailEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiOrderDetailEntity> page = new Query<PsiOrderDetailEntity>(current,size,params).getPage();
        List<PsiOrderDetailEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiOrderDetailEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiOrderDetailEntity entity) {
        boolean added = super.save(entity);
        return added;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiOrderDetailEntity entity) {
        BigDecimal num = stockDao.sumStockBySku(null,entity.getId(),null,null,null);
        num = num.abs();
        if (num.compareTo(entity.getNum()) >0){
            throw new BusinessException("请先修改或删除该商品的出入库记录。您输入的订单商品订购数量不能小于该商品已经出入库的数量");
        }
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>()
            .eq(PsiStockEntity::getDetailId,id)
        );
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        Arrays.stream(ids).forEach(id->{
            stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>()
                .eq(PsiStockEntity::getDetailId,id)
            );
        });
        return super.removeByIds(Arrays.asList(ids));
    }
}
