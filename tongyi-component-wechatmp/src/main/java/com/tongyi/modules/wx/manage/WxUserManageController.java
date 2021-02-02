package com.tongyi.modules.wx.manage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.wx.service.WxUserService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tongyi.modules.wx.entity.WxUser;
import com.tongyi.common.utils.PageUtils;


/**
 * 用户表
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/manage/wxUser")
public class WxUserManageController {
    @Autowired
    private WxUserService userService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:wxuser:list")
    public RestResponse list(@CookieValue String appid, @RequestParam Map<String, Object> params) {
        params.put("appid",appid);
        PageUtils page = new PageUtils(userService.queryPage(params));

        return RestResponse.success().put("page", page);
    }

    /**
     * 列表
     */
    @PostMapping("/listByIds")
    @RequiresPermissions("wx:wxuser:list")
    public RestResponse listByIds(@CookieValue String appid,@RequestBody String[] openids){
        List<WxUser> users = (List<WxUser>) userService.listByIds(Arrays.asList(openids));
        return RestResponse.success(users);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{openid}")
    @RequiresPermissions("wx:wxuser:info")
    public RestResponse info(@CookieValue String appid,@PathVariable("openid") String openid) {
        WxUser wxUser = userService.getById(openid);

        return RestResponse.success().put("wxUser", wxUser);
    }

    /**
     * 同步用户列表
     */
    @PostMapping("/syncWxUsers")
    @RequiresPermissions("wx:wxuser:save")
    public RestResponse syncWxUsers(@CookieValue String appid) {
        wxMpService.switchoverTo(appid);
        userService.syncWxUsers(appid);

        return RestResponse.success("任务已建立");
    }



    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:wxuser:delete")
    public RestResponse delete(@CookieValue String appid,@RequestBody String[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }

}
