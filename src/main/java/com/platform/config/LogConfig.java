/*
 * 项目名称:platform-plus
 * 类名称:LogConfig.java
 * 包名称:com.platform.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/22 16:33    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.config;

import com.platform.common.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 控制台输出日志配置
 *
 * @author 李鹏军
 */
@Configuration
public class LogConfig implements WebMvcConfigurer {
    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources", "/doc.html");
    }
}
