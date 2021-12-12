package com.tongyi.modules.wx.manage;

import com.tongyi.modules.wx.form.WxQrCodeForm;
import com.tongyi.modules.wx.service.WxQrCodeService;
import com.tongyi.common.utils.PageUtils;
import com.tongyi.modules.wx.entity.WxQrCode;
import com.tongyi.common.utils.RestResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 公众号带参二维码管理
 * https://github.com/Wechat-Group/WxJava/wiki/MP_二维码管理
 */
@RestController
@RequestMapping("/manage/wxQrCode")
public class WxQrCodeManageController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxQrCodeService wxQrCodeService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 创建带参二维码ticket
     */
    @PostMapping("/createTicket")
    @RequiresPermissions("wx:wxqrcode:save")
    public RestResponse createTicket(@CookieValue String appid, @RequestBody WxQrCodeForm form) throws WxErrorException {
        wxMpService.switchoverTo(appid);
        WxMpQrCodeTicket ticket = wxQrCodeService.createQrCode(appid,form);
        return RestResponse.success(ticket);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:wxqrcode:list")
    public RestResponse list(@CookieValue String appid,@RequestParam Map<String, Object> params) {
        params.put("appid",appid);
        PageUtils page = wxQrCodeService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wx:wxqrcode:info")
    public RestResponse info(@CookieValue String appid,@PathVariable("id") Long id) {
        WxQrCode wxQrCode = wxQrCodeService.getById(id);

        return RestResponse.success().put("wxQrCode", wxQrCode);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:wxqrcode:delete")
    public RestResponse delete(@CookieValue String appid,@RequestBody Long[] ids) {
        wxQrCodeService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }
}
