package com.platform.modules.app.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @author 李鹏军
 */
public class MyX509TrustManager implements X509TrustManager {

    /**
     * 检查客户端证书
     *
     * @param chain    chain
     * @param authType authType
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    /**
     * 检查服务器端证书
     *
     * @param chain    chain
     * @param authType authType
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    /**
     * 返回受信任的X509证书数组
     *
     * @return null
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}