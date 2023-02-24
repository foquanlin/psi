package com.tongyi.alibaba;

import com.tongyi.config.AliMaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AliMaProperties.class)
public class AlibabaLoginController {
    @Autowired
    private AliMaProperties aliMaProperties;
    /**
     * 支付宝登录
     */
//    @IgnoreAuth
//    @ApiOperation(value = "支付宝登录")
//    @PostMapping("LoginByAli")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", name = "jsonObject", value = "JSON格式参数", examples = @Example({
//                    @ExampleProperty(mediaType = "code", value = "oxaA11ulr9134oBL9Xscon5at_Gc")
//            }), required = true, dataType = "string")
//    })
//    public RestResponse LoginByAli(@RequestBody LoginRequest jsonObject) {
//        String code = jsonObject.getCode();
//
//        AbstractAssert.isBlank(code, "登录失败：code为空");
//
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliMaProperties.getAppId(),
//                aliMaProperties.getPrivateKey(), "json", "UTF-8", aliMaProperties.getPubKey(), "RSA2");
//        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
//        request.setCode(code);
//        request.setGrantType("authorization_code");
//        try {
//            //code 换取token
//            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
//            String accessToken = oauthTokenResponse.getAccessToken();
//
//            //根据token获取用户头像、昵称等信息
//            AlipayUserInfoShareRequest userInfoShareRequest = new AlipayUserInfoShareRequest();
//            AlipayUserInfoShareResponse userInfoResponse = alipayClient.execute(userInfoShareRequest, accessToken);
//
//            Date nowTime = new Date();
//            MallUserEntity user = userService.getOne(new QueryWrapper<MallUserEntity>().eq("ALI_USER_ID", userInfoResponse.getUserId()));
//            if (null == user) {
//                user = new MallUserEntity();
//                String realName = userInfoResponse.getUserName();
//                if (realName == null) {
//                    realName = CharUtil.getRandomString(12);
//                }
//                user.setUserName("支付宝用户：" + realName);
//                user.setPassword(userInfoResponse.getUserId());
//                user.setRegisterTime(nowTime);
//                user.setRegisterIp(this.getClientIp());
//                user.setLastLoginIp(this.getClientIp());
//                user.setLastLoginTime(nowTime);
//                user.setAliUserId(userInfoResponse.getUserId());
//                user.setHeadImgUrl(userInfoResponse.getAvatar());
//                //性别 0：未知、1：男、2：女
//                //F：女性；M：男性
//                user.setGender("m".equalsIgnoreCase(userInfoResponse.getGender()) ? 1 : 0);
//                user.setNickname(userInfoResponse.getNickName());
//                userService.save(user);
//            } else {
//                user.setLastLoginIp(this.getClientIp());
//                user.setLastLoginTime(nowTime);
//                userService.update(user);
//            }

//            String token = jwtUtils.generateToken(user.getId());
//
//            AbstractAssert.isBlank(token, "登录失败：token生成异常");
//
//            Map<String, Object> resultObj = new HashMap<>();
//            resultObj.put("token", token);
//            resultObj.put("userInfo", userInfoResponse);
//            resultObj.put("userId", user.getId());
//            return RestResponse.success(resultObj);
//        } catch (AlipayApiException e) {
//            log.error("登录失败：" + e.getMessage());
//            return RestResponse.error("登录失败");
//        }
//    }
}
