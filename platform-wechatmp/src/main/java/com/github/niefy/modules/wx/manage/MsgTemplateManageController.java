package com.github.niefy.modules.wx.manage;

import java.util.Arrays;
import java.util.Map;

import com.github.niefy.modules.wx.entity.MsgTemplate;
import com.github.niefy.modules.wx.form.TemplateMsgBatchForm;

import com.platform.common.utils.RestResponse;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import me.chanjar.weixin.common.error.WxErrorException;

import com.github.niefy.modules.wx.service.MsgTemplateService;
import com.github.niefy.modules.wx.service.TemplateMsgService;
import com.platform.common.utils.PageUtils;


/**
 * 消息模板
 *
 * @author niefy
 * @email niefy@qq.com
 * @date 2019-11-12 18:30:15
 */
@RestController
@RequestMapping("/manage/msgTemplate")
public class MsgTemplateManageController {
    @Autowired
    private MsgTemplateService msgTemplateService;
    @Autowired
    private TemplateMsgService templateMsgService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wx:msgtemplate:list")
    public RestResponse list(@CookieValue String appid, @RequestParam Map<String, Object> params) {
        params.put("appid",appid);
        PageUtils page = msgTemplateService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wx:msgtemplate:info")
    public RestResponse info(@PathVariable("id") Long id) {
        MsgTemplate msgTemplate = msgTemplateService.getById(id);

        return RestResponse.success().put("msgTemplate", msgTemplate);
    }
    /**
     * 信息
     */
    @GetMapping("/getByName")
    @RequiresPermissions("wx:msgtemplate:info")
    public RestResponse getByName( String name){
        MsgTemplate msgTemplate = msgTemplateService.selectByName(name);

        return RestResponse.success().put("msgTemplate", msgTemplate);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wx:msgtemplate:save")
    public RestResponse save(@RequestBody MsgTemplate msgTemplate) {
        msgTemplateService.save(msgTemplate);

        return RestResponse.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("wx:msgtemplate:update")
    public RestResponse update(@RequestBody MsgTemplate msgTemplate) {
        msgTemplateService.updateById(msgTemplate);

        return RestResponse.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wx:msgtemplate:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        msgTemplateService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }

    /**
     * 同步公众号模板
     */
    @PostMapping("/syncWxTemplate")
    @RequiresPermissions("wx:msgtemplate:save")
    public RestResponse syncWxTemplate(@CookieValue String appid) throws WxErrorException {
        this.wxMpService.switchoverTo(appid);
        msgTemplateService.syncWxTemplate(appid);
        return RestResponse.success();
    }

    /**
     * 批量向用户发送模板消息
     * 通过用户筛选条件（一般使用标签筛选），将消息发送给数据库中所有符合筛选条件的用户
     */
    @PostMapping("/sendMsgBatch")
    @RequiresPermissions("wx:msgtemplate:save")
    public RestResponse sendMsgBatch(@CookieValue String appid,@RequestBody TemplateMsgBatchForm form) {
        this.wxMpService.switchoverTo(appid);
        templateMsgService.sendMsgBatch(form, appid);
        return RestResponse.success();
    }


}
