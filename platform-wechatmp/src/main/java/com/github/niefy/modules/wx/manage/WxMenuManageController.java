package com.github.niefy.modules.wx.manage;

import com.platform.common.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信公众号菜单管理
 * 官方文档：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html
 * WxJava开发文档：https://github.com/Wechat-Group/WxJava/wiki/MP_自定义菜单管理
 */
@RestController
@RequestMapping("/manage/wxMenu")
@RequiredArgsConstructor
public class WxMenuManageController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WxMpService wxService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 获取公众号菜单
     */
    @GetMapping("/getMenu")
    public RestResponse getMenu(@CookieValue String appid) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpMenu wxMpMenu = wxService.getMenuService().menuGet();
        return RestResponse.success(wxMpMenu);
    }

    /**
     * 创建、更新菜单
     */
    @PostMapping("/updateMenu")
    @RequiresPermissions("wx:menu:save")
    public RestResponse updateMenu(@CookieValue String appid,@RequestBody WxMenu wxMenu) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        wxService.getMenuService().menuCreate(wxMenu);
        return RestResponse.success();
    }
}
