/*
 * 项目名称:platform-plus
 * 类名称:SysConfigService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.SysConfigEntity;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author 李鹏军
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     *
     * @param config config
     */
    void add(SysConfigEntity config);

    /**
     * 更新配置信息
     *
     * @param config config
     */
    void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     *
     * @param key   key
     * @param value value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     *
     * @param ids ids
     */
    void deleteBatch(String[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key key
     * @return String
     */
    String getValue(String key);

    /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz clazz
     * @param <T>   <T>
     * @return <T>
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}
