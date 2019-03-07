/*
 * 项目名称:platform-plus
 * 类名称:RedisCache.java
 * 包名称:com.platform.common.annotation
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/23 18:14    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.annotation;

import com.platform.common.utils.Constant;

import java.lang.annotation.*;

/**
 * 加上该注解，代理service命中缓存则从缓存中读取数据，否则从service业务逻辑获得，并存入缓存
 *
 * @author 李鹏军
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {
    /**
     * 缓存key
     * 若destine为true，存入redis的键为cacheKey的值
     * 若destine为false，存入redis的的键为service方法的 cacheKey:userId_id_packageName.className.methodName
     */
    String cacheKey() default Constant.SYS_CACHE;

    /**
     * 数据缓存时间单位s秒 默认5分钟
     */
    int cacheTime() default 300;

    /**
     * 是否使用指定的key
     * 若为true，存入redis的键为cacheKey的值
     */
    boolean destine() default false;
}
