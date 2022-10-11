/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationGoodsServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiAllocationGoodsDao;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 调拨单明细Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Service("psiAllocationGoodsService")
public class PsiAllocationGoodsServiceImpl extends ServiceImpl<PsiAllocationGoodsDao, PsiAllocationGoodsEntity> implements PsiAllocationGoodsService{

    @Override
    public PsiAllocationGoodsEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiAllocationGoodsEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiAllocationGoodsEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiAllocationGoodsEntity> page = new Query<PsiAllocationGoodsEntity>(current,size,params).getPage();
        List<PsiAllocationGoodsEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiAllocationGoodsEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiAllocationGoodsEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiAllocationGoodsEntity entity) {
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
