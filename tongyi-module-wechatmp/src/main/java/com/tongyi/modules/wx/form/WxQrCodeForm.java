package com.tongyi.modules.wx.form;

import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class WxQrCodeForm {
    @NotEmpty(message = "场景值ID不得为空")
    @Size(min = 1, max = 64, message = "场景值长度限制为1到64")
    private String sceneStr;
    @Max(value = 2592000, message = "过期时间不得超过30天")
    private Integer expireSeconds;
    private Boolean isTemp = true;
}
