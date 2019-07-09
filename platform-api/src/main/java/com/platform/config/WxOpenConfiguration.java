package com.platform.config;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="https://github.com/007gzs">
 */
@Slf4j
@Configuration
@ConditionalOnClass(WxOpenService.class)
@EnableConfigurationProperties(WxOpenProperties.class)
public class WxOpenConfiguration {
    private WxOpenProperties properties;
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    public WxOpenConfiguration(WxOpenProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WxOpenService wxOpenService() {
        WxOpenService wxOpenService = new WxOpenServiceImpl();
        WxOpenInRedisConfigStorage wxMpInRedisConfigStorage = new WxOpenInRedisConfigStorage(jedisPool);
        wxMpInRedisConfigStorage.setComponentAppId(properties.getComponentAppId());
        wxMpInRedisConfigStorage.setComponentAppSecret(properties.getComponentSecret());
        wxMpInRedisConfigStorage.setComponentToken(properties.getComponentToken());
        wxMpInRedisConfigStorage.setComponentAesKey(properties.getComponentAesKey());
        wxOpenService.setWxOpenConfigStorage(wxMpInRedisConfigStorage);
        return wxOpenService;
    }

    @Bean
    public WxOpenMessageRouter openMessageRouter(WxOpenService wxOpenService) {
        final WxOpenMessageRouter wxOpenMessageRouter = new WxOpenMessageRouter(wxOpenService);
        wxOpenMessageRouter.rule().handler((wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
            log.info("\n接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(), wxMpXmlMessage);
            return null;
        }).next();

        return wxOpenMessageRouter;
    }
}
