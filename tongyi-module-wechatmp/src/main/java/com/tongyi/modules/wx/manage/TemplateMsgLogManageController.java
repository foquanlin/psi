package com.tongyi.modules.wx.manage;

import java.util.Arrays;
import java.util.Map;

import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.wx.service.TemplateMsgLogService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tongyi.modules.wx.entity.TemplateMsgLog;
import com.tongyi.common.utils.PageUtils;


/**
 * 模版消息发送记录
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/manage/templateMsgLog")
public class TemplateMsgLogManageController {
    @Autowired
    private TemplateMsgLogService templateMsgLogService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:templatemsglog:list")
    public RestResponse list(@CookieValue String appid, @RequestParam Map<String, Object> params) {
        params.put("appid",appid);
        PageUtils page = templateMsgLogService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{logId}")
    @RequiresPermissions("wx:templatemsglog:info")
    public RestResponse info(@CookieValue String appid,@PathVariable("logId") Integer logId) {
        TemplateMsgLog templateMsgLog = templateMsgLogService.getById(logId);

        return RestResponse.success().put("templateMsgLog", templateMsgLog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wx:templatemsglog:save")
    public RestResponse save(@CookieValue String appid,@RequestBody TemplateMsgLog templateMsgLog) {
        templateMsgLogService.save(templateMsgLog);

        return RestResponse.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("wx:templatemsglog:update")
    public RestResponse update(@CookieValue String appid,@RequestBody TemplateMsgLog templateMsgLog) {
        templateMsgLogService.updateById(templateMsgLog);

        return RestResponse.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:templatemsglog:delete")
    public RestResponse delete(@CookieValue String appid,@RequestBody Integer[] logIds) {
        templateMsgLogService.removeByIds(Arrays.asList(logIds));

        return RestResponse.success();
    }

}
