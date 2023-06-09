package com.tongyi.modules.wx.manage;

import java.util.Arrays;
import java.util.Map;

import com.tongyi.modules.wx.form.WxMsgReplyForm;
import com.tongyi.modules.wx.service.MsgReplyService;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.wx.service.WxMsgService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tongyi.modules.wx.entity.WxMsg;
import com.tongyi.common.utils.PageUtils;


/**
 * 微信消息
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/manage/wxMsg")
public class WxMsgManageController {
    @Autowired
    private WxMsgService wxMsgService;
    @Autowired
    private MsgReplyService msgReplyService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:wxmsg:list")
    public RestResponse list(@CookieValue String appid, @RequestParam Map<String, Object> params){
        params.put("appid",appid);
        PageUtils page = wxMsgService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wx:wxmsg:info")
    public RestResponse info(@CookieValue String appid,@PathVariable("id") Long id){
		WxMsg wxMsg = wxMsgService.getById(id);

        return RestResponse.success().put("wxMsg", wxMsg);
    }

    /**
     * 回复
     */
    @PostMapping("/reply")
    @RequiresPermissions("wx:wxmsg:save")
    public RestResponse reply(@CookieValue String appid,@RequestBody WxMsgReplyForm form){

        msgReplyService.reply(form.getOpenid(),form.getReplyType(),form.getReplyContent());
        return RestResponse.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:wxmsg:delete")
    public RestResponse delete(@CookieValue String appid,@RequestBody Long[] ids){
		wxMsgService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }

}
