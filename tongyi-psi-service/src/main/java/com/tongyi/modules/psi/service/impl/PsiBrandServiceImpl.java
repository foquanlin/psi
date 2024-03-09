/*
 * 项目名称:项目名称
 * 类名称:PsiBrandServiceImpl.java
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
import com.tongyi.modules.psi.dao.PsiBrandDao;
import com.tongyi.modules.psi.entity.PsiBrandEntity;
import com.tongyi.modules.psi.service.PsiBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;


/**
 * 品牌Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Service("psiBrandService")
public class PsiBrandServiceImpl extends ServiceImpl<PsiBrandDao, PsiBrandEntity> implements PsiBrandService{

    @Override
    public PsiBrandEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiBrandEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiBrandEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiBrandEntity> page = new Query<PsiBrandEntity>(current,size,params).getPage();
        List<PsiBrandEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiBrandEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiBrandEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiBrandEntity entity) {
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

    @Override
    public void brandStatus(String id, String status) {
        PsiBrandEntity item = baseMapper.selectById(id);
        item.setStatus(status);
        baseMapper.updateById(item);
    }
}
