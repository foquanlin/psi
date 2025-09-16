/*
 * 项目名称:tongyi-component
 * 类名称:SysConfigServiceImpl.java
 * 包名称:com.tongyi.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.Query;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.dao.SysConfigDao;
import com.tongyi.modules.sys.entity.SysConfigEntity;
import com.tongyi.modules.sys.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 林佛权
 */
@Service("sysConfigService")
@CacheConfig(cacheNames = "config")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {


    @CacheEvict(key = "#key")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
    }

    @Cacheable(key = "#key")
    @Override
    public String getValue(String key) {
        SysConfigEntity config = null;
        if (config == null) {
            config = baseMapper.queryByKey(key);
        }

        return config == null ? null : config.getParamValue();
    }
    @Cacheable(key = "#key")
    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException("获取参数失败");
        }
    }

    @Cacheable(key = "#key")
    @Override
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    @Override
    public SysConfigEntity getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public List<SysConfigEntity> listAll(Map<String, Object> params) {
        return super.baseMapper.listAll(params);
    }

    @Override
    public PageInfo<SysConfigEntity> listPage(int current, int size, Map<String, Object> params) {
        params.put("status","1");
        Page<SysConfigEntity> page = new Query<SysConfigEntity>(current,size,params).getPage();
        List<SysConfigEntity> list = super.baseMapper.listPage(page, params);
        return new PageInfo<SysConfigEntity>(page.getCurrent(),page.getSize(),page.getTotal()).setList(list);
    }

    @CachePut()
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addEntity(SysConfigEntity entity) {
        return super.save(entity);
    }

    @CachePut()
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEntity(SysConfigEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteEntity(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict()
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Serializable[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }    
}
