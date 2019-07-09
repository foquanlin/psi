package com.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 李鹏军
 */
@Data
@ConfigurationProperties(prefix = "ali.ma")
public class AliMaProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String privateKey;

    /**
     * 设置微信公众号的token
     */
    private String pubKey;
}
