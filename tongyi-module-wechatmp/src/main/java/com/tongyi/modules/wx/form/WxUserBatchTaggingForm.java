package com.tongyi.modules.wx.form;

import lombok.Data;


import javax.validation.constraints.NotNull;
@Data
public class WxUserBatchTaggingForm {
    @NotNull(message = "标签ID不得为空")
    private Long tagid;
    @NotNull(message = "openid列表不得为空")
    private String[] openidList;
}
