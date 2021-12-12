/*
 * 项目名称:tongyi-component
 * 类名称:SysConfigService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置信息
 *
 * @author 林佛权
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 根据key，更新value
     *
     * @param key   key
     * @param value value
     */
    void updateValueByKey(String key, String value);

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
