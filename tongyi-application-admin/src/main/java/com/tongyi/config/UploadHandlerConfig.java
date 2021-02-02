/*
 * 项目名称:tongyi-component
 * 类名称:ResourceHandlerConfig.java
 * 包名称:com.tongyi.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/15 09:58    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源配置
 *
 * @author 林佛权
 */
@Component
public class UploadHandlerConfig implements WebMvcConfigurer {
    @Value("${platform.file.path}")
    private String filePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/upload/**")){
            registry.addResourceHandler("/upload/**").addResourceLocations("file:"+filePath);
        }
    }
}
