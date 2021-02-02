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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.sys.dao.SysDictDao;
import com.tongyi.modules.sys.entity.SysDictEntity;
import com.tongyi.modules.sys.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SysDictEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public IPage queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "D.SORT");
        IPage<SysDictEntity> page = new Query<SysDictEntity>(params).getPage();
        return page.setRecords(baseMapper.selectDictPage(page, params));
    }

    @Override
    public void add(SysDictEntity sysDict) {
        this.save(sysDict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictEntity sysDict) {
        this.updateById(sysDict);
    }

    @Override
    public void delete(String id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<SysDictEntity> queryByCode(Map<String, Object> params) {
        return baseMapper.queryByCode(params);
    }
}
