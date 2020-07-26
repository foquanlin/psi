package com.platform.modules.wx.service.impl;

import com.platform.config.TaskExcutor;
import com.platform.modules.wx.entity.MsgReplyRule;
import com.platform.modules.wx.entity.WxMsg;
import com.platform.modules.wx.service.MsgReplyRuleService;
import com.platform.modules.wx.service.MsgReplyService;
import com.platform.modules.wx.service.WxMsgService;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 微信公众号消息处理
 * 官方文档：https://developers.weixin.qq.com/doc/offiaccount/Message_Ma nagement/Service_Center_messages.html#7
 * 参考WxJava客服消息文档：https://github.com/Wechat-Group/WxJava/wiki/MP_主动发送消息（客服消息）
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MsgReplyServiceImpl implements MsgReplyService {
    @Autowired
    MsgReplyRuleService msgReplyRuleService;
    @Autowired
    WxMpService wxMpService;
    @Value("${wx.mp.autoReplyInterval:1000}")
    Long autoReplyInterval;
    @Autowired
    WxMsgService wxMsgService;

    private Gson gson = new Gson();

    /**
     * 根据规则配置通过微信客服消息接口自动回复消息
     *
     *
     * @param appid 公众号appid
     * @param exactMatch 是否精确匹配
     * @param toUser     用户openid
     * @param keywords   匹配关键词
     * @return 是否已自动回复，无匹配规则则不自动回复
     */
    @Override
    public boolean tryAutoReply(String appid, boolean exactMatch, String toUser, String keywords) {
        try {
            List<MsgReplyRule> rules = msgReplyRuleService.getMatchedRules(appid,exactMatch, keywords);
            if (rules.isEmpty()) {
                return false;
            }
            long delay = 0;
            for (MsgReplyRule rule : rules) {
                TaskExcutor.schedule(() -> {
                    wxMpService.switchover(appid);
                    this.reply(toUser,rule.getReplyType(),rule.getReplyContent());
                }, delay, TimeUnit.MILLISECONDS);
                delay += autoReplyInterval;
            }
            return true;
        } catch (Exception e) {
            log.error("自动回复出错：", e);
        }
        return false;
    }

    @Override
    public void replyText(String toUser, String content) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.TEXT().toUser(toUser).content(content).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("content",content);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.TEXT,toUser,json));
    }

    @Override
    public void replyImage(String toUser, String mediaId) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.IMAGE().toUser(toUser).mediaId(mediaId).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("mediaId",mediaId);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.IMAGE,toUser,json));
    }

    @Override
    public void replyVoice(String toUser, String mediaId) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.VOICE().toUser(toUser).mediaId(mediaId).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("mediaId",mediaId);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.VOICE,toUser,json));
    }

    @Override
    public void replyVideo(String toUser, String mediaId) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.VIDEO().toUser(toUser).mediaId(mediaId).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("mediaId",mediaId);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.VIDEO,toUser,json));
    }

    @Override
    public void replyMusic(String toUser, String musicInfoJson) throws WxErrorException {
        JsonElement parser = JsonParser.parseString(musicInfoJson);
        JsonObject json = parser.getAsJsonObject();
        wxMpService.getKefuService().sendKefuMessage(
            WxMpKefuMessage.MUSIC().toUser(toUser)
                .musicUrl(json.get("musicurl").getAsString())
                .hqMusicUrl(json.get("hqmusicurl").getAsString())
                .title(json.get("title").getAsString())
                .description(json.get("description").getAsString())
                .thumbMediaId(json.get("thumb_media_id").getAsString())
                .build());

        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.IMAGE,toUser,musicInfoJson));
    }

    /**
     * 发送图文消息（点击跳转到外链） 图文消息条数限制在1条以内
     * @param toUser
     * @param newsInfoJson
     * @throws WxErrorException
     */
    @Override
    public void replyNews(String toUser, String newsInfoJson) throws WxErrorException {
        WxMpKefuMessage.WxArticle wxArticle = gson.fromJson(newsInfoJson, WxMpKefuMessage.WxArticle.class);
        List<WxMpKefuMessage.WxArticle> newsList = new ArrayList<WxMpKefuMessage.WxArticle>(){{add(wxArticle);}};
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.NEWS().toUser(toUser).articles(newsList).build());

        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.NEWS,toUser,gson.fromJson(newsInfoJson,HashMap.class)));
    }

    /**
     * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在1条以内
     * @param toUser
     * @param mediaId
     * @throws WxErrorException
     */
    @Override
    public void replyMpNews(String toUser, String mediaId) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.MPNEWS().toUser(toUser).mediaId(mediaId).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("mediaId",mediaId);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.MPNEWS,toUser,json));
    }

    @Override
    public void replyWxCard(String toUser, String cardId) throws WxErrorException {
        wxMpService.getKefuService().sendKefuMessage(WxMpKefuMessage.WXCARD().toUser(toUser).cardId(cardId).build());

        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put("cardId",cardId);
        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.WXCARD,toUser,json));
    }

    @Override
    public void replyMiniProgram(String toUser, String miniProgramInfoJson) throws WxErrorException {
        JsonElement parser = JsonParser.parseString(miniProgramInfoJson);
        JsonObject json = parser.getAsJsonObject();
        wxMpService.getKefuService().sendKefuMessage(
            WxMpKefuMessage.MINIPROGRAMPAGE()
                .toUser(toUser)
                .title(json.get("title").getAsString())
                .appId(json.get("appid").getAsString())
                .pagePath(json.get("pagepath").getAsString())
                .thumbMediaId(json.get("thumb_media_id").getAsString())
                .build());

        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.IMAGE,toUser,miniProgramInfoJson));
    }

    @Override
    public void replyMsgMenu(String toUser, String msgMenusJson) throws WxErrorException {
        JsonElement parser = JsonParser.parseString(msgMenusJson);
//        WxMpKefuMessage.MsgMenu.class
        List<WxMpKefuMessage.MsgMenu> msgMenus = new ArrayList<>();
        JsonObject json = parser.getAsJsonObject();
        JsonArray array = json.getAsJsonArray("list");
        Type type = new TypeToken<WxMpKefuMessage.MsgMenu>() {}.getType();
        for(int i=0;i<array.size();i++){
            JsonElement el = array.get(i);
            WxMpKefuMessage.MsgMenu tmp = gson.fromJson(el, type);
            msgMenus.add(tmp);
        }
        wxMpService.getKefuService().sendKefuMessage(
            WxMpKefuMessage.MSGMENU()
                .toUser(toUser)
                .headContent(json.get("head_content").getAsString())
                .tailContent(json.get("tail_content").getAsString())
                .msgMenus(msgMenus).build());

        wxMsgService.addWxMsg(WxMsg.buildOutMsg(WxConsts.KefuMsgType.IMAGE,toUser,msgMenusJson));
    }

}
