package com.tongyi.alipay.config;

import com.tongyi.alipay.service.AlipayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * wechat ma configuration
 *
 * @author 林佛权
 */
@Configuration
@EnableConfigurationProperties(AliPayProperties.class)
public class AlipayConfiguration {

    private AliPayProperties properties;

    @Autowired
    public AlipayConfiguration(AliPayProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AlipayServiceImpl alipayService(){
        return new AlipayServiceImpl(this.properties);
    }

}
