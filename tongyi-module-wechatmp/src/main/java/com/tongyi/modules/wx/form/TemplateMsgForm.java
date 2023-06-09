package com.tongyi.modules.wx.form;

import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.JsonUtils;
import lombok.Data;

@Data
public class TemplateMsgForm {
    private String openid;
    private String msg;
    private String template;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public boolean isValid() {
        if (openid == null || openid.isEmpty() || msg == null || msg.isEmpty() || template == null || template.isEmpty()) {
            throw new BusinessException("缺少必要参数");
        }
        return true;
    }
}
