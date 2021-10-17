package com.tongyi.alipay.config;

import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @author 林佛权
 */
@Data
@ConfigurationProperties(prefix = "ali.pay")
public class AliPayProperties {

    private String protocol = "https";
    private String gatewayHost = "openapi.alipay.com"; // "https://openapi.alipay.com/gateway.do";//"
    private String signType = "RSA2";
    /**
     * 应用的appid
     */
    private String appId;

    /**
     * 请填写您的应用私钥，例如：MIIEvQIBADANB
     */
    private String appPrivateKey;
    /**
     * 请填写您的支付宝公钥
     */
    public String alipayPublicKey;
    //////////////有了私钥就不用下面的公钥//////////////////////////////
    /**
     * 请填写您的应用公钥证书文件路径
     */
    private String appCertPath;
    /**
     * 请填写您的支付宝公钥证书文件路径
     */
    private String alipayCertPath;
    /**
     * 请填写您的支付宝根证书文件路径
     */
    private String alipayRootCertPath;
    /**
     * 请填写您的支付类接口异步通知接收服务地址
     */
    private String notifyUrl;
    /**
     * 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA==
     */
    private String encryptKey;

    public Config newConfig(){
        Config config = new Config();
        config.protocol = this.protocol;
        config.gatewayHost = this.gatewayHost;
        config.signType = this.signType;
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.alipayPublicKey;

        config.merchantCertPath = this.appCertPath;
        config.alipayCertPath = this.alipayCertPath;
        config.alipayRootCertPath = this.alipayRootCertPath;
        config.notifyUrl = this.notifyUrl;
        config.encryptKey = this.encryptKey;
        return config;
    }
}
