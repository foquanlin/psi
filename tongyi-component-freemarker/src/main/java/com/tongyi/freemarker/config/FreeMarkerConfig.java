package com.tongyi.freemarker.config;

import freemarker.cache.HttpTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


@Configuration
public class FreeMarkerConfig {
    @Bean
    public FreeMarkerViewResolver newViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        return resolver;
    }

    @Bean
    public freemarker.template.Configuration newConfiguration(){
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        //设置默认生成文件编码
        configuration.setDefaultEncoding("utf-8");
        //设置模板路径
        //configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setTemplateLoader(new HttpTemplateLoader("http://www.wxngrok.com/"));
        return configuration;
    }
}
