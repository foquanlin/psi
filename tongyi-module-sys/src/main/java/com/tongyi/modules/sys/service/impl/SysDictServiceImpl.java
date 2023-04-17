/*
 * 项目名称:tongyi-component
 * 类名称:SysDictServiceImpl.java
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
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysDictDao;
import com.tongyi.modules.sys.entity.SysDictEntity;
import com.tongyi.modules.sys.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据字典Service实现类
 *
 * @author 林佛权
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public List<SysDictEntity> queryByCode(Map<String, Object> params) {
        return baseMapper.queryByCode(params);
    }

    @Override
    public SysDictEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysDictEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysDictEntity> listPage(int current, int size, Map<String, Object> params) {
        //排序
        params.put("sidx", "d.sort");
        Page<SysDictEntity> page = new Query<SysDictEntity>(current,size,params).getPage();
        List<SysDictEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysDictEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysDictEntity entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysDictEntity entity) {
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
