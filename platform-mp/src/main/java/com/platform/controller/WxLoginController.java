package com.platform.controller;

import com.platform.common.utils.JwtUtils;
import com.platform.common.utils.RestResponse;
import com.platform.modules.app.entity.UserEntity;
import com.platform.modules.tb.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/wx")
public class WxLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    private final WxMpService wxService;

    /**
     * 微信登录
     *
     * @param code code
     * @return RestResponse
     */
    @PostMapping("loginByCode")
    @ApiOperation(value = "微信登录", notes = "根据微信code登录，每一个code只能使用一次")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", value = "code", required = true, dataType = "string", example = "011g526m1UaFqp0ixb9m1PhX5m1g526x")
    })
    public RestResponse loginByCode(String code) throws WxErrorException {
        WxMpOAuth2AccessToken auth2AccessToken = wxService.oauth2getAccessToken(code);

        String openId = auth2AccessToken.getOpenId();

        //生成token
        String token = jwtUtils.generateToken(openId);

        //获取微信用户信息
        WxMpUser wxMpUser = wxService.oauth2getUserInfo(auth2AccessToken, null);

        //保存或者更新
        UserEntity user = userService.saveOrUpdateByOpenId(wxMpUser);

        Map<String, Object> map = new HashMap<>(8);
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("openId", openId);
        map.put("user", user);

        return RestResponse.success(map);
    }
}
