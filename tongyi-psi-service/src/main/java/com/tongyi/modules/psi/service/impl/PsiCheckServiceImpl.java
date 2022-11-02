/*
 * 项目名称:项目名称
 * 类名称:PsiCheckServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiCheckDao;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.entity.PsiWarehouseEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import com.tongyi.modules.psi.service.PsiCheckService;
import com.tongyi.modules.psi.service.PsiWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 盘点Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Service("psiCheckService")
public class PsiCheckServiceImpl extends ServiceImpl<PsiCheckDao, PsiCheckEntity> implements PsiCheckService{

    @Autowired
    private PsiWarehouseService warehouseService;
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
//         return super.removeByIds(Arrays.asList(ids));
    }

    @Transactional
    @Override
    public void addCheck(String userId, String warehouseId, String memo, List<PsiCheckDetailEntity> list) {
        PsiWarehouseEntity warehouse = warehouseService.getById(warehouseId);
        if (null == warehouse){
            throw new BusinessException("没有这个仓库");
        }
        if (PsiWarehouseEntity.Status.RUN != PsiWarehouseEntity.Status.valueOf(warehouse.getStatus())){
            throw new BusinessException("仓库未启用");
        }
        PsiCheckEntity entity = PsiCheckEntity.newEntity(userId,warehouseId,memo);
        this.addEntity(entity);
        list.forEach(item->{
            PsiCheckDetailEntity detail = PsiCheckDetailEntity.newEntity(entity,item);
            checkDetailService.addEntity(detail);
        });
    }
}
