package com.tongyi.modules.wx.form;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class WxMsgReplyForm {
    @NotEmpty(message = "用户信息不得为空")
    private String openid;
    @NotEmpty(message = "回复类型不得为空")
    private String replyType;
    @NotEmpty(message = "回复内容不得为空")
    private String replyContent;
}
