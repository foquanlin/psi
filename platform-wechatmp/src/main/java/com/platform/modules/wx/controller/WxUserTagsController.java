package com.platform.modules.wx.controller;

import com.platform.modules.wx.entity.WxUser;
import com.platform.modules.wx.form.WxUserTaggingForm;
import com.platform.modules.wx.service.WxUserService;
import com.platform.modules.wx.service.WxUserTagsService;
import com.platform.common.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 粉丝标签
 */
@RestController
@RequestMapping("/wxUserTags")
@RequiredArgsConstructor
public class WxUserTagsController {
    @Autowired
    WxUserTagsService wxUserTagsService;
    @Autowired
    WxUserService wxUserService;
    private final WxMpService wxMpService;

    @GetMapping("/userTags")
    public RestResponse userTags(@CookieValue String appid, @CookieValue String openid){
        if(openid==null){
            return RestResponse.error("none_openid");
        }
        this.wxMpService.switchoverTo(appid);
        WxUser wxUser = wxUserService.getById(openid);
        if(wxUser==null){
            wxUser=wxUserService.refreshUserInfo(openid,appid);
            if(wxUser==null) {
                return RestResponse.error("not_subscribed");
            }
        }
        return RestResponse.success(wxUser.getTagidList());
    }

    @PostMapping("/tagging")
    public RestResponse tagging(@CookieValue String appid,@CookieValue String openid , @RequestBody WxUserTaggingForm form) {
        this.wxMpService.switchoverTo(appid);
        try {
            wxUserTagsService.tagging(form.getTagid(),openid);
        }catch (WxErrorException e){
            WxError error = e.getError();
            if(50005==error.getErrorCode()){//未关注公众号
                return RestResponse.error("not_subscribed");
            }else {
                return RestResponse.error(error.getErrorMsg());
            }
        }
        return RestResponse.success();
    }

    @PostMapping("/untagging")
    public RestResponse untagging(@CookieValue String appid,@CookieValue String openid , @RequestBody WxUserTaggingForm form) throws WxErrorException {
        this.wxMpService.switchoverTo(appid);
        wxUserTagsService.untagging(form.getTagid(),openid);
        return RestResponse.success();
    }
}
