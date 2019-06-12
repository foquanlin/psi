/*
 * 项目名称:platform-plus
 * 类名称:SysDictGroupServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.utils.Query;
import com.platform.modules.sys.dao.SysDictGroupDao;
import com.platform.modules.sys.entity.SysDictGroupEntity;
import com.platform.modules.sys.service.SysDictGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据字典分组Service实现类
 *
 * @author 李鹏军
 * @date 2019-01-15 11:42:20
 */
@Service("sysDictGroupService")
public class SysDictGroupServiceImpl extends ServiceImpl<SysDictGroupDao, SysDictGroupEntity> implements SysDictGroupService {

    @Override
    public List<SysDictGroupEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.CREATE_TIME");
        params.put("asc", false);
        Page<SysDictGroupEntity> page = new Query<SysDictGroupEntity>(params).getPage();
        return page.setRecords(baseMapper.selectSysDictGroupPage(page, params));
    }

    @Override
    public void add(SysDictGroupEntity sysDictGroup) {
        sysDictGroup.setCreateTime(new Date());
        this.save(sysDictGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictGroupEntity sysDictGroup) {
        this.updateById(sysDictGroup);
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
}
