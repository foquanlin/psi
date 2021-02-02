package com.tongyi.modules.wx.controller;

import com.tongyi.modules.wx.entity.WxUser;
import com.tongyi.modules.wx.service.WxUserService;
import com.tongyi.common.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信用户（粉丝）
 */
@RestController
@RequestMapping("/wxUser")
@RequiredArgsConstructor
public class WxUserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WxUserService wxUserService;
    private final WxMpService wxMpService;

    @GetMapping("/getUserInfo")
    public RestResponse getUserInfo(@CookieValue String appid, @CookieValue String openid){
        this.wxMpService.switchoverTo(appid);
        WxUser wxUser = wxUserService.getById(openid);
        return RestResponse.success(wxUser);
    }
}
