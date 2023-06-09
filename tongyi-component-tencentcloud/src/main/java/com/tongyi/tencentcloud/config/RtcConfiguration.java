package com.tongyi.tencentcloud.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tongyi.tencentcloud.rtc.service.impl.CallBackServiceImpl;
import com.tongyi.tencentcloud.rtc.service.impl.CallQualityServiceImpl;
import com.tongyi.tencentcloud.rtc.service.impl.RoomServiceImpl;
import com.tongyi.tencentcloud.rtc.service.impl.TranscodeServiceImpl;
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
@EnableConfigurationProperties(RtcProperties.class)
public class RtcConfiguration {

    private RtcProperties properties;

    @Autowired
    public RtcConfiguration(RtcProperties properties) {
        this.properties = properties;
    }

    @Bean
    public TrtcClient newTrtcClient(){
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        Credential cred = properties.newCredential();
        ClientProfile clientProfile = properties.newProfile();
        // 实例化要请求产品(以cvm为例)的client对象,clientProfile是可选的
        TrtcClient client = new TrtcClient(cred, properties.getRegion(), clientProfile);
        return client;
    }

    @Bean
    public TranscodeServiceImpl transcodeServiceImpl(){
        return new TranscodeServiceImpl(newTrtcClient(),properties.getAppid());
    }

    @Bean
    public RoomServiceImpl roomService(){
        return new RoomServiceImpl(newTrtcClient(),properties.getAppid());
    }

    @Bean
    public CallQualityServiceImpl callQualityServiceImpl(){
        return new CallQualityServiceImpl(newTrtcClient(),properties.getAppid());
    }
    @Bean
    public CallBackServiceImpl callBackService(){
        return new CallBackServiceImpl(newTrtcClient(),properties.getAppid(),properties.getCallbackKey());
    }

}
