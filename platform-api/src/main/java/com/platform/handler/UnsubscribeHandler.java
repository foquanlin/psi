package com.platform.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.common.utils.Constant;
import com.platform.modules.sys.entity.TbUserEntity;
import com.platform.modules.sys.service.TbUserService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信公众号消息处理（取消关注）
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {
    @Autowired
    private TbUserService userService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);

        TbUserEntity entity = userService.getOne(new QueryWrapper<TbUserEntity>().eq("OPEN_ID", openId));

        entity.setSubscribe(Constant.UNSUBSCRIBE);
        userService.updateById(entity);
        return null;
    }

}
