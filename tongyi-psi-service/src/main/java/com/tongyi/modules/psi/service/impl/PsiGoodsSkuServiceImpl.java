/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiGoodsSkuDao;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.psi.service.PsiGoodsSkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 商品skuService实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 */
@Service("psiGoodsSkuService")
public class PsiGoodsSkuServiceImpl extends ServiceImpl<PsiGoodsSkuDao, PsiGoodsSkuEntity> implements PsiGoodsSkuService{

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
        return super.removeByIds(Arrays.asList(ids));
    }
}
