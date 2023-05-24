/*
 * 项目名称:进销存
 * 类名称:PsiFinanceDetailServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiFinanceDetailDao;
import com.tongyi.modules.psi.entity.PsiFinanceDetailEntity;
import com.tongyi.modules.psi.service.PsiFinanceDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 非销售明细Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@Service("psiFinanceDetailService")
public class PsiFinanceDetailServiceImpl extends ServiceImpl<PsiFinanceDetailDao, PsiFinanceDetailEntity> implements PsiFinanceDetailService{

    @Override
    public PsiFinanceDetailEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiFinanceDetailEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiFinanceDetailEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiFinanceDetailEntity> page = new Query<PsiFinanceDetailEntity>(current,size,params).getPage();
        List<PsiFinanceDetailEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiFinanceDetailEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiFinanceDetailEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiFinanceDetailEntity entity) {
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
