/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiGoodsDao;
import com.tongyi.modules.psi.entity.PsiGoodsEntity;
import com.tongyi.modules.psi.service.PsiGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 商品Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Service("psiGoodsService")
public class PsiGoodsServiceImpl extends ServiceImpl<PsiGoodsDao, PsiGoodsEntity> implements PsiGoodsService{

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
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiGoodsEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiGoodsEntity entity) {
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
