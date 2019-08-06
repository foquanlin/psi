package com.platform.modules.app.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.web.bind.annotation.*;

/**
 * 微信公众号菜单管理
 *
 * @author 李鹏军
 */
@AllArgsConstructor
@RestController
@RequestMapping("/app/wx/menu")
public class WxMenuController {
    private final WxMpService wxService;

    /**
     * <pre>
     * 自定义菜单创建接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     * 如果要创建个性化菜单，请设置matchrule属性
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @PostMapping("/create")
    public String menuCreate(@RequestBody String json) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(json);
    }

    @GetMapping("/create")
    public String menuCreateSample() throws WxErrorException {
        WxMenuButton mainBtn1 = new WxMenuButton();
        mainBtn1.setName("公众号商城");
        mainBtn1.setType(WxConsts.MenuButtonType.VIEW);
        mainBtn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb340c435ee3873dc&redirect_uri=https%3a%2f%2ffly2you.cn%2fgzh%2f%23%2findex%2fhome&response_type=code&scope=snsapi_userinfo&state=STAT#wechat_redirect");

        WxMenuButton mainBtn2 = new WxMenuButton();
        mainBtn2.setName("小程序商城");
        mainBtn2.setType(WxConsts.MenuButtonType.MINIPROGRAM);
        mainBtn2.setUrl("http://mp.weixin.qq.com");
        mainBtn2.setAppId("wxeca4341756496160");
        mainBtn2.setPagePath("/pages/index/index");

        WxMenuButton btn31 = new WxMenuButton();
        btn31.setName("历史消息");
        btn31.setType(WxConsts.MenuButtonType.VIEW);
        btn31.setUrl("https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=Mzg4MTIzNjM2OQ==#wechat_redirect");

        WxMenuButton btn32 = new WxMenuButton();
        btn32.setType(WxConsts.MenuButtonType.VIEW);
        btn32.setName("开源地址");
        btn32.setUrl("https://gitee.com/fuyang_lipengjun/platform-plus");

        WxMenuButton btn33 = new WxMenuButton();
        btn33.setName("微信点餐");
        btn33.setType(WxConsts.MenuButtonType.VIEW);
        btn33.setUrl("https://fly2you.cn/mp");

        WxMenuButton mainBtn3 = new WxMenuButton();
        mainBtn3.setName("更多");
        mainBtn3.getSubButtons().add(btn31);
        mainBtn3.getSubButtons().add(btn32);
        mainBtn3.getSubButtons().add(btn33);

        WxMenu menu = new WxMenu();
        menu.getButtons().add(mainBtn1);
        menu.getButtons().add(mainBtn2);
        menu.getButtons().add(mainBtn3);

        return this.wxService.getMenuService().menuCreate(menu);
    }

    /**
     * <pre>
     * 自定义菜单删除接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
     * </pre>
     */
    @GetMapping("/delete")
    public void menuDelete() throws WxErrorException {
        this.wxService.getMenuService().menuDelete();
    }

    /**
     * <pre>
     * 删除个性化菜单接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @param menuId 个性化菜单的menuid
     */
    @GetMapping("/delete/{menuId}")
    public void menuDelete(@PathVariable String menuId) throws WxErrorException {
        this.wxService.getMenuService().menuDelete(menuId);
    }

    /**
     * <pre>
     * 自定义菜单查询接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
     * </pre>
     */
    @GetMapping("/get")
    public WxMpMenu menuGet() throws WxErrorException {
        return this.wxService.getMenuService().menuGet();
    }

    /**
     * <pre>
     * 测试个性化菜单匹配结果
     * 详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
     * </pre>
     *
     * @param userid 可以是粉丝的OpenID，也可以是粉丝的微信号。
     */
    @GetMapping("/menuTryMatch/{userid}")
    public WxMenu menuTryMatch(@PathVariable String userid) throws WxErrorException {
        return this.wxService.getMenuService().menuTryMatch(userid);
    }

    /**
     * <pre>
     * 获取自定义菜单配置接口
     * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     * 请注意：
     * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
     * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
     * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
     * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
     * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
     *  接口调用请求说明:
     * http请求方式: GET（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
     * </pre>
     */
    @GetMapping("/getSelfMenuInfo")
    public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
        return this.wxService.getMenuService().getSelfMenuInfo();
    }
}
