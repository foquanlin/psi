/*
 * 项目名称:platform-plus
 * 类名称:RedisCacheAspect.java
 * 包名称:com.platform.common.aspect
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/23 18:15    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.annotation.RedisCache;
import com.platform.common.utils.JedisUtil;
import com.platform.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 李鹏军
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private JedisUtil jedisUtil;

    @Pointcut("@annotation(com.platform.common.annotation.RedisCache)")
    public void webAspect() {
    }

    @SuppressWarnings("unchecked")
    @Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //得到类名、方法名和参数
        String redisResult;
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        //得到被代理的方法
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //得到被代理的方法上的注解
        String cacheKey = method.getAnnotation(RedisCache.class).cacheKey();
        int cacheTime = method.getAnnotation(RedisCache.class).cacheTime();
        boolean destine = method.getAnnotation(RedisCache.class).destine();

        String key = cacheKey;
        if (!destine) {
            //根据类名，方法名和参数生成key
            key = StringUtils.genKey(cacheKey, className, methodName);
        }
        log.debug("生成的key[{}]", key);

        Object result;
        if (!jedisUtil.exists(key)) {
            log.debug("缓存未命中");
            //缓存不存在，则调用原方法，并将结果放入缓存中
            result = pjp.proceed(args);
            redisResult = JSON.toJSONString(result);
            jedisUtil.setObject(key, redisResult, cacheTime);
        } else {
            //缓存命中
            log.debug("缓存命中");
            redisResult = JSONObject.toJSON(jedisUtil.getObject(key)).toString();
            //得到被代理方法的返回值类型
            Class returnType = method.getReturnType();
            result = JSON.parseObject(redisResult, returnType);
        }
        return result;
    }
}