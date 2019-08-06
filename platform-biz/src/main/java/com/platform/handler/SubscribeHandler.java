package com.platform.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.builder.TextBuilder;
import com.platform.common.utils.Constant;
import com.platform.common.utils.DateUtils;
import com.platform.modules.mall.entity.MallUserEntity;
import com.platform.modules.mall.service.MallUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 微信公众号消息处理（用户关注）
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Autowired
    private MallUserService userService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                this.logger.info("用户: " + userWxInfo.toString());
                MallUserEntity entity = userService.getOne(new QueryWrapper<MallUserEntity>().eq("OPEN_ID", userWxInfo.getOpenId()));
                if (entity == null) {
                    entity = new MallUserEntity();
                    entity.setRegisterTime(new Date());
                }
                entity.setUserName(userWxInfo.getNickname());
                entity.setNickname(userWxInfo.getNickname());
                entity.setHeadImgUrl(userWxInfo.getHeadImgUrl());
                entity.setMpOpenId(userWxInfo.getOpenId());
                entity.setGender(userWxInfo.getSex());
                entity.setSubscribe(Constant.SUBSCRIBE);
                entity.setSubscribeTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
                userService.saveOrUpdate(entity);
            }
        } catch (WxErrorException e) {
            this.logger.error(e.getLocalizedMessage());
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("您好，欢迎关注安徽微同科技有限公司微信公众号，您可以直接回复消息发现更多服务（如：文档、官网）。", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
