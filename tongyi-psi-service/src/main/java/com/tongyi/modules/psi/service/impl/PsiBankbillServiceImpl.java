/*
 * 项目名称:进销存系统
 * 类名称:PsiBankbillServiceImpl.java
 * 包名称:com.tongyi.modules.psi.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service.impl;
import com.tongyi.core.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.psi.dao.PsiBankbillDao;
import com.tongyi.modules.psi.entity.PsiBankbillEntity;
import com.tongyi.modules.psi.service.PsiBankbillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * 银行账单Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 */
@Service("psiBankbillService")
public class PsiBankbillServiceImpl extends ServiceImpl<PsiBankbillDao, PsiBankbillEntity> implements PsiBankbillService{

    @Override
    public PsiBankbillEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<PsiBankbillEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<PsiBankbillEntity> listPage(int current, int size,Map<String, Object> params) {
        Page<PsiBankbillEntity> page = new Query<PsiBankbillEntity>(current,size,params).getPage();
        List<PsiBankbillEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<PsiBankbillEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(PsiBankbillEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(PsiBankbillEntity entity) {
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
