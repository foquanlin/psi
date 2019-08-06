package com.platform.handler;

import com.platform.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信公众号消息处理（地理位置）
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class LocationHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        //上报地理位置事件
        this.logger.info("上报地理位置，msgType:" + wxMessage.getMsgType() + "，纬度 : {}，经度 : {}，精度 : {}",
                wxMessage.getLatitude(), wxMessage.getLongitude(), String.valueOf(wxMessage.getPrecision()));

        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用

        String content = "感谢反馈，您的的地理位置已收到！纬度 : " + wxMessage.getLatitude() + "，经度 : " + wxMessage.getLongitude() + "，精度 : " + String.valueOf(wxMessage.getPrecision());
        return new TextBuilder().build(content, wxMessage, wxMpService);
    }

}
