package com.platform.modules.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信消息
 * 
 * @author niefy
 * @date 2020-05-14 17:28:34
 */
@Data
@TableName("wx_msg")
public class WxMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	private String appid;
	/**
	 * 微信用户ID
	 */
	private String openid;
	/**
	 * 消息方向
	 */
	private byte inOut;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息详情
	 */
	private String detail;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public static class WxMsgInOut{
		static final byte IN=0;
		static final byte OUT=1;
	}

	public WxMsg() {
	}
	public WxMsg(WxMpXmlMessage wxMessage) {
		this.openid=wxMessage.getFromUser();
		this.appid= WxMpConfigStorageHolder.get();
		this.inOut = WxMsgInOut.IN;
		this.msgType = wxMessage.getMsgType();
		Map<String,Object> detail = new HashMap<String,Object>();
		Long createTime = wxMessage.getCreateTime();
		this.createTime = createTime==null?new Date():new Date(createTime*1000);
		if(WxConsts.XmlMsgType.TEXT.equals(this.msgType)){
			detail.put("content",wxMessage.getContent());
		}else if(WxConsts.XmlMsgType.IMAGE.equals(this.msgType)){
			detail.put("picUrl",wxMessage.getPicUrl());
			detail.put("mediaId",wxMessage.getMediaId());
		}else if(WxConsts.XmlMsgType.VOICE.equals(this.msgType)){
			detail.put("format",wxMessage.getFormat());
			detail.put("mediaId",wxMessage.getMediaId());
		}else if(WxConsts.XmlMsgType.VIDEO.equals(this.msgType) ||
				WxConsts.XmlMsgType.SHORTVIDEO.equals(this.msgType)){
			detail.put("thumbMediaId",wxMessage.getThumbMediaId());
			detail.put("mediaId",wxMessage.getMediaId());
		}else if(WxConsts.XmlMsgType.LOCATION.equals(this.msgType)){
			detail.put("locationX",wxMessage.getLocationX());
			detail.put("locationY",wxMessage.getLocationY());
			detail.put("scale",wxMessage.getScale());
			detail.put("label",wxMessage.getLabel());
		}else if(WxConsts.XmlMsgType.LINK.equals(this.msgType)){
			detail.put("title",wxMessage.getTitle());
			detail.put("description",wxMessage.getDescription());
			detail.put("url",wxMessage.getUrl());
		}else if(WxConsts.XmlMsgType.EVENT.equals(this.msgType)){
			detail.put("event",wxMessage.getEvent());
			detail.put("eventKey",wxMessage.getEventKey());
		}
	}
	public static WxMsg buildOutMsg(String msgType, String openid, Map<String,Object> detail){
		WxMsg wxMsg = new WxMsg();
		wxMsg.appid= WxMpConfigStorageHolder.get();
		wxMsg.msgType = msgType;
		wxMsg.openid = openid;
		wxMsg.detail = new Gson().toJson(detail);
		wxMsg.createTime=new Date();
		wxMsg.inOut = WxMsgInOut.OUT;
		return wxMsg;
	}
	public static WxMsg buildOutMsg(String msgType, String openid, String detail){
		WxMsg wxMsg = new WxMsg();
		wxMsg.appid= WxMpConfigStorageHolder.get();
		wxMsg.msgType = msgType;
		wxMsg.openid = openid;
		wxMsg.detail = detail;
		wxMsg.createTime=new Date();
		wxMsg.inOut = WxMsgInOut.OUT;
		return wxMsg;
	}
}
