package com.tongyi.tencentcloud.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * @author 林佛权
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud")
public class RtcProperties {

    /**
     * 接口地址
     */
    private String url = "https://trtc.tencentcloudapi.com";
    /**
     * 指定接入地域域名
     */
    private String endpoint;
    /**
     * 应用的secretId
     */
    private String secretId;

    /**
     * 请填写您的应用私钥，例如：MIIEvQIBADANB
     */
    private String secretKey;

    /**
     * 地域
     */
    private String region;

    /**
     * 版本
     */
    private String version;
    private Long appid;

    private String reqMethod = "POST";
    private Integer connTimeout = 30;
    private Integer writeTimeout = 30;
    private Integer readTimeout = 30;
    private String signMethod = "HmacSHA256";
    private boolean debug= false;
    private String language;

    private String callbackKey = "kutian1234";

    public Credential newCredential(){
        Credential cred = new Credential(secretId, secretKey);
        return cred;
    }
    public ClientProfile newProfile(){

        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        //  从3.1.16版本开始, 单独设置 HTTP 代理
        // httpProfile.setProxyHost("真实代理ip");
        // httpProfile.setProxyPort(真实代理端口);
        httpProfile.setReqMethod(reqMethod); // get请求(默认为post请求)
        httpProfile.setConnTimeout(connTimeout); // 请求连接超时时间，单位为秒(默认60秒)
        httpProfile.setWriteTimeout(writeTimeout);  // 设置写入超时时间，单位为秒(默认0秒)
        httpProfile.setReadTimeout(readTimeout);  // 设置读取超时时间，单位为秒(默认0秒)
        if (!StringUtils.isEmpty(endpoint)) {
            httpProfile.setEndpoint(endpoint); // 指定接入地域域名(默认就近接入)
        }

        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(signMethod); // 指定签名算法(默认为HmacSHA256)
        // 自3.1.80版本开始，SDK 支持打印日志。
        clientProfile.setHttpProfile(httpProfile);
        clientProfile.setDebug(debug);
        // 从3.1.16版本开始，支持设置公共参数 Language, 默认不传，选择(ZH_CN or EN_US)
        if (!StringUtils.isEmpty(language)) {
            clientProfile.setLanguage(Language.valueOf(language));
        }
        return clientProfile;
    }
}
