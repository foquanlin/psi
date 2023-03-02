/*
 * 项目名称:tongyi-component
 * 类名称:SysDictGroupServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysDictGroupDao;
import com.tongyi.modules.sys.entity.SysDictGroupEntity;
import com.tongyi.modules.sys.service.SysDictGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 数据字典分组Service实现类
 *
 * @author 林佛权
 */
@Service("sysDictGroupService")
public class SysDictGroupServiceImpl extends ServiceImpl<SysDictGroupDao, SysDictGroupEntity> implements SysDictGroupService {

    @Override
    public SysDictGroupEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysDictGroupEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysDictGroupEntity> listPage(int current, int size, Map<String, Object> params) {
        //排序
        params.put("sidx", "t.create_time");
        params.put("asc", false);
        Page<SysDictGroupEntity> page = new Query<SysDictGroupEntity>(current,size,params).getPage();
        List<SysDictGroupEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysDictGroupEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysDictGroupEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysDictGroupEntity entity) {
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
    public SysDictGroupEntity getByCode(String code) {
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        List<SysDictGroupEntity> list = super.baseMapper.listAll(params);
        if (null!=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
