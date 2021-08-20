package com.tongyi.freemarker.config;

import freemarker.cache.HttpTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.cache.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.freemarker.TongyiFreeMarkerView;
import org.springframework.web.servlet.view.freemarker.TongyiFreeMarkerViewResolver;


@Configuration
public class FreeMarkerConfig {

    //    @Bean
    public FreeMarkerConfigurer newConfig(){
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        config.getConfiguration().setTemplateUpdateDelayMilliseconds(0);
        config.getConfiguration().setCacheStorage(new NullCacheStorage());
        return config;
    }
    @Bean
//    @Order(1)
    public ViewResolver newViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setRequestContextAttribute("request");
//        TongyiFreeMarkerViewResolver resolver = new TongyiFreeMarkerViewResolver();

        return resolver;
    }

    //    @Bean
    public freemarker.template.Configuration newConfiguration(){
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        //设置默认生成文件编码
        configuration.setDefaultEncoding("utf-8");
        //设置模板路径
        //configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        TemplateLoader[] loaders = new TemplateLoader[]{new HttpTemplateLoader("http://www.wxngrok.com/")};
        MultiTemplateLoader loader = new MultiTemplateLoader(loaders);
        configuration.setTemplateLoader(loader);
        return configuration;
    }
}
