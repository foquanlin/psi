/*
 * 项目名称:platform-plus
 * 类名称:SysConfigServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.platform.common.utils.JedisUtil;
import com.platform.modules.sys.entity.SysCacheEntity;
import com.platform.modules.sys.service.SysCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 李鹏军
 */
@Service("sysCacheService")
public class SysCacheServiceImpl implements SysCacheService {
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public List<SysCacheEntity> queryAll(Map<String, String> params) {
        SysCacheEntity sysCacheEntity;
        List<SysCacheEntity> result = new ArrayList<>();

        String pattern = params.get("pattern");
        // 获取所有key
        Set<String> keySet = jedisUtil.keys(pattern);
        for (String key : keySet) {
            sysCacheEntity = new SysCacheEntity();
            sysCacheEntity.setCacheKey(key);
            try {
                sysCacheEntity.setValue(JSONObject.toJSON(jedisUtil.getObject(key)).toString());
            } catch (Exception e) {
                sysCacheEntity.setValue("");
            }
            sysCacheEntity.setSeconds(jedisUtil.ttl(key));
            result.add(sysCacheEntity);
        }
        return result;
    }

    @Override
    public int deleteBatch(String[] keys) {
        for (String key : keys) {
            jedisUtil.del(key);
            jedisUtil.delObject(key);
        }
        return keys.length;
    }
}
