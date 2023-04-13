/*
 * 项目名称:tongyi-component
 * 类名称:WebMvcConfig.java
 * 包名称:com.tongyi.modules.app.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.config;

import com.tongyi.interceptor.AuthorizationInterceptor;
import com.tongyi.resolver.LoginUserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置
 *
 * @author 林佛权
 */
@Configuration
@ConfigurationProperties(prefix = "tongyi.path")
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    private String[] includes;
    private String[] excludes;
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
//    @Autowired
//    private LoginUserResolver loginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration inter = registry.addInterceptor(authorizationInterceptor);
        inter.excludePathPatterns(new String[]{"/swagger-resources/**","/swagger-resources","/v3/api-docs"});
        if (null != includes ) {
            inter.addPathPatterns(includes);
        }
        if (null !=excludes) {
            inter.excludePathPatterns(excludes);
        }
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
//    }
}
