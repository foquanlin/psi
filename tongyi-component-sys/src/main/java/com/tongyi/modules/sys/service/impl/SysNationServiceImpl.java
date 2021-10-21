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
import com.tongyi.modules.sys.dao.SysNationDao;
import com.tongyi.modules.sys.entity.SysNationEntity;
import com.tongyi.modules.sys.service.SysNationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SysNationEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "t.code");
        params.put("asc", false);
        Page<SysNationEntity> page = new Query<SysNationEntity>(params).getPage();
        return page.setRecords(baseMapper.selectSysNationPage(page, params));
    }

    @Override
    @Transactional
    public boolean add(SysNationEntity sysNation) {
        return this.save(sysNation);
    }

    @Override
    @Transactional
    public boolean update(SysNationEntity sysNation) {
        return this.updateById(sysNation);
    }

    @Override
    @Transactional
    public boolean delete(String code) {
        return this.removeById(code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(String[] codes) {
        return this.removeByIds(Arrays.asList(codes));
    }
}
