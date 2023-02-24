package com.tongyi.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {
    private WxPayProperties properties;

    @Autowired
    public WxPayConfiguration(WxPayProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        if (null==properties || StringUtils.isEmpty(properties.getAppId())) {
            return null;
        }
        WxPayConfig payConfig = new WxPayConfig();

        payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(this.properties.getMchKey()));
        payConfig.setApiV3Key(StringUtils.trimToNull(this.properties.getMchKey3()));
        payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
        payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
        payConfig.setKeyPath(StringUtils.trimToNull(this.properties.getKeyPath()));
        payConfig.setPrivateKeyPath(StringUtils.trimToNull(this.properties.getPrivateKeyPath()));
        payConfig.setPrivateCertPath(StringUtils.trimToNull(this.properties.getPrivateCertPath()));


        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

}
