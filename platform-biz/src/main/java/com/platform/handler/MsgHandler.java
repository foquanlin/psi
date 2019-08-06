package com.platform.handler;


import com.platform.builder.TextBuilder;
import com.platform.common.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * 微信公众号消息处理（消息处理）
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {
        String msgType = wxMessage.getMsgType();
        String content = wxMessage.getContent();

        //当用户输入关键词如“你好”，“在线客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            switch (msgType) {
                case XmlMsgType.TEXT:
                    if (StringUtils.startsWithAny(content, "你好", "在线客服")
                            && weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
                        return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                                .fromUser(wxMessage.getToUser())
                                .toUser(wxMessage.getFromUser()).build();
                    } else if (StringUtils.equalsAny(content, "官网")) {
                        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                        item.setTitle("微同商城官网");
                        item.setPicUrl("https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/pwm/logo.png");
                        item.setUrl("http://fly2you.cn");
                        return WxMpXmlOutMessage.NEWS().addArticle(item)
                                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                                .build();
                    } else if (StringUtils.equals(content, "文档")) {
                        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                        item.setTitle("platform-wechat-mall开发文档1.0.4.pdf");
                        item.setPicUrl("https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/pwm/logo.png");
                        item.setUrl("http://fly2you.cn/guide/index");
                        return WxMpXmlOutMessage.NEWS().addArticle(item)
                                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                                .build();
                    }
                    break;
                case XmlMsgType.IMAGE:
                    //图片
                    break;
                case XmlMsgType.VOICE:
                case XmlMsgType.SHORTVIDEO:
                case XmlMsgType.VIDEO:
                    //音频
                    break;
                default:
                    //TODO 组装回复消息
                    String contentJson = "收到信息内容：" + JsonUtils.toJson(wxMessage);
                    return new TextBuilder().build(contentJson, wxMessage, weixinService);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

}
