package com.tongyi.modules.wx.form;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class WxUserTaggingForm {
    @NotNull(message = "标签ID不得为空")
    private Long tagid;
}
