package com.tongyi.config;

import com.tongyi.common.utils.StringUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * wechat mp configuration
 *
 * @author 林佛权
 */
//@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
//    @Autowired
//    private LogHandler logHandler;
//    @Autowired
//    private NullHandler nullHandler;
//    @Autowired
//    private KfSessionHandler kfSessionHandler;
//    @Autowired
//    private StoreCheckNotifyHandler storeCheckNotifyHandler;
//    @Autowired
//    private LocationHandler locationHandler;
//    @Autowired
//    private MenuHandler menuHandler;
//    @Autowired
//    private MsgHandler msgHandler;
//    @Autowired
//    private UnsubscribeHandler unsubscribeHandler;
//    @Autowired
//    private SubscribeHandler subscribeHandler;
//    @Autowired
//    private ScanHandler scanHandler;
    @Autowired
    private WxMpProperties properties;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpDefaultConfigImpl wxMpInRedisConfigStorage = new WxMpDefaultConfigImpl();
        if (null!=properties&& !StringUtils.isEmpty(properties.getAppId())) {
            wxMpInRedisConfigStorage.setAppId(properties.getAppId());
            wxMpInRedisConfigStorage.setSecret(properties.getSecret());
            wxMpInRedisConfigStorage.setToken(properties.getToken());
            wxMpInRedisConfigStorage.setAesKey(properties.getAesKey());
            wxMpService.setWxMpConfigStorage(wxMpInRedisConfigStorage);
        }
        return wxMpService;
    }

//    @Bean
//    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
//        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
//
//        // 记录所有事件的日志 （异步执行）
//        newRouter.rule().handler(this.logHandler).next();
//
//        // 接收客服会话管理事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
//                .handler(this.kfSessionHandler).end();
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
//                .handler(this.kfSessionHandler)
//                .end();
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
//                .handler(this.kfSessionHandler).end();
//
//        // 门店审核事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
//                .handler(this.storeCheckNotifyHandler).end();
//
//        // 自定义菜单事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.MenuButtonType.CLICK).handler(this.menuHandler).end();
//
//        // 点击菜单连接事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.MenuButtonType.VIEW).handler(this.nullHandler).end();
//
//        // 关注事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.EventType.SUBSCRIBE).handler(this.subscribeHandler)
//                .end();
//
//        // 取消关注事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.EventType.UNSUBSCRIBE)
//                .handler(this.unsubscribeHandler).end();
//
//        // 上报地理位置事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.EventType.LOCATION).handler(this.locationHandler)
//                .end();
//
//        // 接收地理位置消息
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
//                .handler(this.locationHandler).end();
//
//        // 扫码事件
//        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
//                .event(WxConsts.EventType.SCAN).handler(this.scanHandler).end();
//
//        // 默认
//        newRouter.rule().async(false).handler(this.msgHandler).end();
//
//        return newRouter;
//    }

}
