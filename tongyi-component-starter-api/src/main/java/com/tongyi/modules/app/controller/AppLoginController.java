/*
 * 项目名称:tongyi-component
 * 类名称:AppLoginController.java
 * 包名称:com.tongyi.modules.app.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.app.controller;

//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipaySystemOauthTokenRequest;
//import com.alipay.api.request.AlipayUserInfoShareRequest;
//import com.alipay.api.response.AlipaySystemOauthTokenResponse;
//import com.alipay.api.response.AlipayUserInfoShareResponse;
//import com.tongyi.config.AliMaProperties;
//import com.tongyi.modules.mall.entity.MallUserEntity;
//import com.tongyi.modules.mall.service.MallUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP登录授权
 *
 * @author 林佛权
 */
@Slf4j
@RestController
@RequestMapping("/app/auth")
@Api(tags = "AppLoginController|APP登录接口")
//@EnableConfigurationProperties(AliMaProperties.class)
public class AppLoginController extends AppBaseController {
//    @Autowired
//    private MallUserService userService;
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private WxMaService wxMaService;
//    @Autowired
//    private WxMpService wxMpService;
//    @Autowired
//    private AliMaProperties aliMaProperties;

    /**
     * 用户名密码登录
     *
     * @return RestResponse
     */
//    @IgnoreAuth
//    @PostMapping("loginByMobile")
//    @ApiOperation(value = "手机号密码登录", notes = "根据手机号密码登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "mobile", value = "15209831990"),
//                    @ExampleProperty(mediaType = "password", value = "admin")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse loginByMobile(@RequestBody LoginRequest jsonObject) {
//        String mobile = jsonObject.getMobile();
//        AbstractAssert.isBlank(mobile, "手机号不能为空");
//
//        String password = jsonObject.getPassword();
//        AbstractAssert.isBlank(password, "密码不能为空");
//        //用户登录
//        MallUserEntity user = userService.loginByMobile(mobile, password);
//
//        //生成token
//        String token = jwtUtils.generateToken(user.getId());
//
//        Map<String, Object> map = new HashMap<>(4);
//        map.put("token", token);
//        map.put("openId", user.getOpenId());
//        map.put("user", user);
//        map.put("expire", jwtUtils.getExpire());
//
//        return RestResponse.success(map);
//    }

    /**
     * 微信小程序登录
     */
//    @IgnoreAuth
//    @PostMapping("LoginByMa")
//    @ApiOperation(value = "微信小程序登录", notes = "wx.login()每次返回的code只能使用一次")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "code", value = "oxaA11ulr9134oBL9Xscon5at_Gc"),
//                    @ExampleProperty(mediaType = "userInfo", value = "wx.login()返回的userInfo信息，JSON格式参数")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse LoginByMa(@RequestBody LoginRequest jsonObject) {
//        FullUserInfo fullUserInfo = null;
//        String code = jsonObject.getCode();
//        AbstractAssert.isBlank(code, "登录失败：code为空");
//
//        if (null != jsonObject.getUserInfo()) {
//            fullUserInfo = jsonObject.getUserInfo();
//        }
//        AbstractAssert.isNull(fullUserInfo, "登录失败：userInfo为空");
//
//        try {
//            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
//            // 用户信息校验
//            log.info("》》》微信返回sessionData：" + session.toString());
//
//            if (!wxMaService.getUserService().checkUserInfo(session.getSessionKey(), fullUserInfo.getRawData(), fullUserInfo.getSignature())) {
//                log.error("登录失败：数据签名验证失败");
//                return RestResponse.error("登录失败");
//            }
//
//            // 解密用户信息
//            WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(session.getSessionKey(), fullUserInfo.getEncryptedData(), fullUserInfo.getIv());
//
//            Date nowTime = new Date();
//            MallUserEntity user = userService.selectByOpenId(wxMaUserInfo.getOpenId());
//            if (null == user) {
//                user = new MallUserEntity();
//                user.setUserName(wxMaUserInfo.getNickName());
//                user.setPassword(wxMaUserInfo.getOpenId());
//                user.setRegisterTime(nowTime);
//                user.setRegisterIp(this.getClientIp());
//                user.setOpenId(wxMaUserInfo.getOpenId());
//                user.setHeadImgUrl(wxMaUserInfo.getAvatarUrl());
//                //性别 0：未知、1：男、2：女
//                user.setGender(Integer.parseInt(wxMaUserInfo.getGender()));
//                user.setNickname(wxMaUserInfo.getNickName());
//                userService.save(user);
//            } else {
//                user.setLastLoginIp(this.getClientIp());
//                user.setLastLoginTime(nowTime);
//                user.setUserName(wxMaUserInfo.getNickName());
//                user.setHeadImgUrl(wxMaUserInfo.getAvatarUrl());
//                user.setGender(Integer.parseInt(wxMaUserInfo.getGender()));
//                user.setNickname(wxMaUserInfo.getNickName());
//                userService.update(user);
//            }
//
//            String token = jwtUtils.generateToken(user.getId());
//
//            if (null == wxMaUserInfo || StringUtils.isNullOrEmpty(token)) {
//                log.error("登录失败：token生成异常");
//                return RestResponse.error("登录失败");
//            }
//            return RestResponse.success().put("token", token).put("userInfo", wxMaUserInfo).put("userId", user.getId());
//        } catch (WxErrorException e) {
//            log.error("登录失败：" + e.getMessage());
//            return RestResponse.error("登录失败");
//        }
//    }

    /**
     * 微信公众号登录
     *
     * @return RestResponse
     */
//    @IgnoreAuth
//    @PostMapping("loginByMp")
//    @ApiOperation(value = "微信公众号登录", notes = "根据微信code登录，每一个code只能使用一次")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "code", value = "oxaA11ulr9134oBL9Xscon5at_Gc")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse loginByMp(@RequestBody LoginRequest jsonObject) {
//        String code = jsonObject.getCode();
//
//        AbstractAssert.isBlank(code, "登录失败：code为空");
//
//        Map<String, Object> map = new HashMap<>(8);
//        try {
//            WxMpOAuth2AccessToken auth2AccessToken = wxMpService.oauth2getAccessToken(code);
//
//            String openId = auth2AccessToken.getOpenId();
//
//            //获取微信用户信息
//            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(auth2AccessToken, null);
//
//            //保存或者更新
//            MallUserEntity user = userService.saveOrUpdateByOpenId(wxMpUser);
//
//            //生成token
//            String token = jwtUtils.generateToken(user.getId());
//
//            map.put("token", token);
//            map.put("expire", jwtUtils.getExpire());
//            map.put("openId", openId);
//            map.put("user", user);
//        } catch (WxErrorException e) {
//            log.error("登录失败：" + e.getMessage());
//            return RestResponse.error("登录失败");
//        }
//
//        return RestResponse.success(map);
//    }


    /**
     * QQ小程序登录
     */
//    @IgnoreAuth
//    @PostMapping("LoginByQQ")
//    @ApiOperation(value = "QQ小程序登录", notes = "qq.login()每次返回的code只能使用一次")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "code", value = "oxaA11ulr9134oBL9Xscon5at_Gc"),
//                    @ExampleProperty(mediaType = "userInfo", value = "qq.login()返回的userInfo信息，JSON格式参数")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse LoginByQQ(@RequestBody LoginRequest jsonObject) {
//        FullUserInfo fullUserInfo = null;
//        String code = jsonObject.getCode();
//        AbstractAssert.isBlank(code, "登录失败：code为空");
//
//        if (null != jsonObject.getUserInfo()) {
//            fullUserInfo = jsonObject.getUserInfo();
//        }
//        AbstractAssert.isNull(fullUserInfo, "登录失败：userInfo为空");
//
//        try {
//            Map<String, String> params = new HashMap<>(8);
//            params.put("appid", appid);
//            params.put("secret", secret);
//            params.put("js_code", code);
//            params.put("grant_type", "authorization_code");
//
//            String result = wxMaService.get("https://api.q.qq.com/sns/jscode2session", Joiner.on("&").withKeyValueSeparator("=").join(params));
//            WxMaJscode2SessionResult session = WxMaJscode2SessionResult.fromJson(result);
//            // 用户信息校验
//            log.info("》》》QQ返回sessionData：" + session.toString());
//
//            if (!wxMaService.getUserService().checkUserInfo(session.getSessionKey(), fullUserInfo.getData(), fullUserInfo.getSignature())) {
//                log.error("登录失败：数据签名验证失败");
//                return RestResponse.error("登录失败");
//            }
//
//            // 解密用户信息
//            WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(session.getSessionKey(), fullUserInfo.getEncryptedData(), fullUserInfo.getIv());
//
//            Date nowTime = new Date();
//            MallUserEntity user = userService.selectByOpenId(wxMaUserInfo.getOpenId());
//            if (null == user) {
//                user = new MallUserEntity();
//                user.setUserName(wxMaUserInfo.getNickName());
//                user.setPassword(wxMaUserInfo.getOpenId());
//                user.setRegisterTime(nowTime);
//                user.setRegisterIp(this.getClientIp());
//                user.setQqOpenId(wxMaUserInfo.getOpenId());
//                user.setHeadImgUrl(wxMaUserInfo.getAvatarUrl());
//                //性别 0：未知、1：男、2：女
//                user.setGender(Integer.parseInt(wxMaUserInfo.getGender()));
//                user.setNickname(wxMaUserInfo.getNickName());
//                userService.save(user);
//            } else {
//                user.setLastLoginIp(this.getClientIp());
//                user.setLastLoginTime(nowTime);
//                user.setUserName(wxMaUserInfo.getNickName());
//                user.setHeadImgUrl(wxMaUserInfo.getAvatarUrl());
//                user.setGender(Integer.parseInt(wxMaUserInfo.getGender()));
//                user.setNickname(wxMaUserInfo.getNickName());
//                userService.update(user);
//            }
//
//            String token = jwtUtils.generateToken(user.getId());
//
//            if (null == wxMaUserInfo || StringUtils.isNullOrEmpty(token)) {
//                log.error("登录失败：token生成异常");
//                return RestResponse.error("登录失败");
//            }
//            return RestResponse.success().put("token", token).put("userInfo", wxMaUserInfo).put("userId", user.getId());
//        } catch (WxErrorException e) {
//            log.error("登录失败：" + e.getMessage());
//            return RestResponse.error("登录失败");
//        }
//    }

    /**
     * 根据openId换取登录token，方便本地开发调试
     *
     * @return RestResponse
     */
//    @IgnoreAuth
//    @PostMapping("loginByOpenId")
//    @ApiOperation(value = "openId换取登录token", notes = "根据openId换取登录token，方便本地开发调试")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "openId", value = "ok8KW5GEIwAYTa-Z92JfbzxkVNpA")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse loginByOpenId(@RequestBody LoginRequest jsonObject) {
//        String openId = jsonObject.getOpenId();
//        MallUserEntity user = userService.selectByOpenId(openId);
//        AbstractAssert.isNull(user, "登录失败：用户为空");
//
//        //生成token
//        String token = jwtUtils.generateToken(user.getId());
//        Map<String, Object> map = new HashMap<>(8);
//        map.put("token", token);
//        map.put("expire", jwtUtils.getExpire());
//        map.put("openId", openId);
//        map.put("user", user);
//
//        return RestResponse.success(map);
//    }
}
