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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.tongyi.common.exception.BusinessException;
//import com.tongyi.common.utils.JedisUtil;
import com.tongyi.common.utils.Query;
import com.tongyi.modules.sys.dao.SysConfigDao;
import com.tongyi.modules.sys.entity.SysConfigEntity;
import com.tongyi.modules.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * @author 林佛权
 */
@Service("sysConfigService")
@CacheConfig(cacheNames = "config")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {
//    @Autowired
//    private JedisUtil jedisUtils;
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Page queryPage(Map<String, Object> params) {
        String paramKey = (String) params.get("paramKey");
        Page<SysConfigEntity> page = new Query<SysConfigEntity>(params).getPage();
        return (Page) baseMapper.selectPage(page,
                new QueryWrapper<SysConfigEntity>()
                        .like(StringUtils.isNotBlank(paramKey), "PARAM_KEY", paramKey)
                        .eq("STATUS", 1));
    }
    @CachePut()
    @Override
    public void add(SysConfigEntity config) {
        this.save(config);
//        saveOrUpdateFromRedis(config);
    }

    @CachePut()
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfigEntity config) {
        baseMapper.updateById(config);
//        saveOrUpdateFromRedis(config);
    }

    @CacheEvict(key = "#key")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
//        deleteFromRedis(key);
    }

    @CacheEvict()
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] ids) {
//        for (String id : ids) {
//            SysConfigEntity config = this.getById(id);
//            deleteFromRedis(config.getParamKey());
//        }

        this.removeByIds(Arrays.asList(ids));
    }
    @Cacheable(key = "#key")
    @Override
    public String getValue(String key) {
        SysConfigEntity config = null;//getFromRedis(key);
        if (config == null) {
            config = baseMapper.queryByKey(key);
//            saveOrUpdateFromRedis(config);
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

//    private void saveOrUpdateFromRedis(SysConfigEntity config) {
//        if (config == null) {
//            return;
//        }
//        String key = Constant.SYS_CACHE + config.getParamKey();
//        redisTemplate.opsForValue().set(key,config);
////        jedisUtils.setObject(key, config);
//    }
    @Cacheable(key = "#key")
    @Override
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

//    private void deleteFromRedis(String configKey) {
//        String key = Constant.SYS_CACHE + configKey;
////        jedisUtils.del(key);
//        redisTemplate.delete(key);
//    }

//    private SysConfigEntity getFromRedis(String configKey) {
//        String key = Constant.SYS_CACHE + configKey;
//        return (SysConfigEntity) redisTemplate.opsForValue().get(key);
////        return (SysConfigEntity) jedisUtils.getObject(key);
//    }
}
