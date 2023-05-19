/*
 * 项目名称:项目名称
 * 类名称:SysNationServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysNationDao;
import com.tongyi.modules.sys.entity.SysNationEntity;
import com.tongyi.modules.sys.service.SysNationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 民族Service实现类
 *
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 */
@Service("sysNationService")
public class SysNationServiceImpl extends ServiceImpl<SysNationDao, SysNationEntity> implements SysNationService {

    @Override
    public SysNationEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysNationEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysNationEntity> listPage(int current, int size, Map<String, Object> params) {
        //排序
        params.put("sidx", "t.code");
        params.put("asc", false);
        Page<SysNationEntity> page = new Query<SysNationEntity>(current,size,params).getPage();
        List<SysNationEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysNationEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysNationEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysNationEntity entity) {
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
