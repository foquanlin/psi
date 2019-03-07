package com.platform.modules.app.utils;

import com.alibaba.fastjson.JSONObject;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.platform.common.utils.JedisUtil;
import com.platform.common.utils.StringUtils;
import com.platform.modules.app.config.WeChatConfig;
import com.platform.modules.app.entity.UserEntity;
import com.platform.modules.app.entity.WxOauth2Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 李鹏军
 */
@Slf4j
@Component
public class WechatUtil {
    @Autowired
    JedisUtil jedisUtil;

    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            StringBuilder buffer = new StringBuilder();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }

    /**
     * 获取网页授权凭证
     *
     * @param code code
     * @return WeixinAouth2Token
     */
    public WxOauth2Token getOauth2AccessToken(String code) {
        WxOauth2Token wxOauth2Token = null;
        // 拼接请求地址
        String requestUrl = WeChatConfig.OAUTH2_ACCESS_TOKEN_URL;
        requestUrl = requestUrl.replace("APPID", WeChatConfig.APP_ID);
        requestUrl = requestUrl.replace("SECRET", WeChatConfig.APP_SECRET);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        log.info(">>>>>>>>>>>>>>>>>>> 获取网页授权凭证：" + jsonObject);
        if (null != jsonObject) {
            int errorCode = jsonObject.getIntValue("errcode");
            if (errorCode != 0) {
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, jsonObject.getString("errmsg"));
                throw new BusinessException("获取网页授权凭证失败：" + jsonObject.getString("errmsg"));
            } else {
                wxOauth2Token = new WxOauth2Token();
                wxOauth2Token.setAccessToken(jsonObject.getString("access_token"));
                wxOauth2Token.setExpiresIn(jsonObject.getIntValue("expires_in"));
                wxOauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
                wxOauth2Token.setOpenId(jsonObject.getString("openid"));
                wxOauth2Token.setScope(jsonObject.getString("scope"));
            }
        }
        return wxOauth2Token;
    }

    /**
     * 获取用户信息
     *
     * @param openId      用户标识
     * @param accessToken accessToken
     * @param count       重试次数
     * @return UserEntity
     */
    public UserEntity getUserInfo(String openId, String accessToken, int count) {
        UserEntity weixinUserInfo = null;
        // 拼接请求地址
        String requestUrl = WeChatConfig.USER_INFO_URL;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        log.info(">>>>>>>>>>>>>>>>>>> 微信用户信息：" + jsonObject);
        if (null != jsonObject) {

            int errorCode = jsonObject.getIntValue("errcode");
            if (errorCode != 0) {
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, jsonObject.getString("errmsg"));
                accessToken = refreshToken();
                if (count > 0) {
                    count--;
                    getUserInfo(openId, accessToken, count);
                }
            } else {
                weixinUserInfo = new UserEntity();
                weixinUserInfo.setCreateTime(new Date());
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            }
        }
        return weixinUserInfo;
    }

    /**
     * 刷新AccessToken，并保存到redis
     *
     * @return accessToken
     */
    public String refreshToken() {
        //这个url链接地址和参数皆不能变
        String requestUrl = WeChatConfig.ACCESS_TOKEN_URL;

        JSONObject accessToken = httpsRequest(requestUrl, "GET", null);

        log.info("JSON字符串：" + accessToken);

        String strAccessToken = accessToken.getString("access_token");
        if (StringUtils.isNotBlank(strAccessToken)) {
            jedisUtil.setObject(Constant.ACCESS_TOKEN, accessToken, 7200);
        }
        return strAccessToken;
    }
}
