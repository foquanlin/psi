/*
 * 项目名称:进销存
 * 类名称:PsiFinanceServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiFinanceDao;
import com.tongyi.modules.psi.dao.PsiFinanceDetailDao;
import com.tongyi.modules.psi.entity.PsiFinanceDetailEntity;
import com.tongyi.modules.psi.entity.PsiFinanceEntity;
import com.tongyi.modules.psi.service.PsiFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 非销售Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@Service("psiFinanceService")
public class PsiFinanceServiceImpl extends ServiceImpl<PsiFinanceDao, PsiFinanceEntity> implements PsiFinanceService{

    @Autowired
    private PsiFinanceDetailDao financeDetailDao;

    @Override
    public PsiFinanceEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiFinanceEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiFinanceEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiFinanceEntity> page = new Query<PsiFinanceEntity>(current,size,params).getPage();
        List<PsiFinanceEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiFinanceEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiFinanceEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiFinanceEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
//        financeDetailDao.delete(new LambdaQueryWrapper<PsiFinanceDetailEntity>().eq(PsiFinanceDetailEntity::getFid,id));
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
//        financeDetailDao.delete(new LambdaQueryWrapper<PsiFinanceDetailEntity>().in(PsiFinanceDetailEntity::getFid,ids));
        return super.removeByIds(Arrays.asList(ids));
    }
}
