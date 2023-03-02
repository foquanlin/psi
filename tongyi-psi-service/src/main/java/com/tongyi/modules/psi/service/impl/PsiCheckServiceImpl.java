/*
 * 项目名称:项目名称
 * 类名称:PsiCheckServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiCheckDao;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import com.tongyi.modules.psi.service.PsiCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;

/**
 * 盘点Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Service("psiCheckService")
public class PsiCheckServiceImpl extends ServiceImpl<PsiCheckDao, PsiCheckEntity> implements PsiCheckService{

    @Autowired
    private PsiCheckDetailService checkDetailService;

    @Override
    public PsiCheckEntity getById(Serializable id){
        return baseMapper.selectById(id);
    }

    @Override
    public List<PsiCheckEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiCheckEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiCheckEntity> page = new Query<PsiCheckEntity>(current,size,params).getPage();
        List<PsiCheckEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiCheckEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiCheckEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiCheckEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        checkDetailService.deleteByCid(new Serializable[]{id});
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.baseMapper.deleteById(ids) >0;
    }

}
