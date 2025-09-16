package com.tongyi.modules.wx.form;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class WxUserTagForm {
    private Long id;
    @NotEmpty(message = "标签名称不得为空")
    @Size(min = 1,max = 30,message = "标签名称长度必须为1-30字符")
    private String name;
}
