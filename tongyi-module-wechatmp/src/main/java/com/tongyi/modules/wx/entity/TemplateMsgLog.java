package com.tongyi.modules.wx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.tongyi.common.utils.JsonUtils;
import lombok.Data;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板消息日志
 * @author 林佛权
 */
@Data
@TableName("wx_template_msg_log")
public class TemplateMsgLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private int logId;
    private String appid;
    private String touser;
    private String templateId;
    private JsonArray data;
    private String url;
    private String miniprogram;
    private Date sendTime;
    private String sendResult;

    public TemplateMsgLog() {
    }

    public TemplateMsgLog(WxMpTemplateMessage msg,String appid, String sendResult) {
        this.appid = appid;
        this.touser = msg.getToUser();
        this.templateId = msg.getTemplateId();
        this.url = msg.getUrl();
        this.miniprogram = new Gson().toJson(msg.getMiniProgram());
        this.data = JsonParser.parseString(new Gson().toJson(msg.getData())).getAsJsonArray();
        this.sendTime = new Date();
        this.sendResult = sendResult;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
