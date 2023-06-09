/*
 * 项目名称:项目名称
 * 类名称:PsiCheckDetailServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiCheckDetailDao;
import com.tongyi.modules.psi.dao.PsiStockDao;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 盘点明细Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Service("psiCheckDetailService")
public class PsiCheckDetailServiceImpl extends ServiceImpl<PsiCheckDetailDao, PsiCheckDetailEntity> implements PsiCheckDetailService{

    @Autowired
    private PsiStockDao stockDao;
    @Override
    public PsiCheckDetailEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiCheckDetailEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiCheckDetailEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiCheckDetailEntity> page = new Query<PsiCheckDetailEntity>(current,size,params).getPage();
        List<PsiCheckDetailEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiCheckDetailEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiCheckDetailEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiCheckDetailEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        PsiCheckDetailEntity item = baseMapper.selectById(id);
        stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>()
                .eq(PsiStockEntity::getOrderId,item.getCid())
                .eq(PsiStockEntity::getGoodsId,item.getGoodsId())
                .eq(PsiStockEntity::getSkuId,item.getSkuId()));
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        Arrays.stream(ids).forEach(id->{
            PsiCheckDetailEntity item = baseMapper.selectById(id);
            stockDao.delete(new LambdaQueryWrapper<PsiStockEntity>()
                    .eq(PsiStockEntity::getOrderId,item.getCid())
                    .eq(PsiStockEntity::getGoodsId,item.getGoodsId())
                    .eq(PsiStockEntity::getSkuId,item.getSkuId()));
        });
        return super.removeByIds(Arrays.asList(ids));
    }
}
