package com.tongyi.modules.wx.form;

import com.tongyi.common.utils.JsonUtils;
import lombok.Data;

@Data
public class AccountBindForm {
    String phoneNum;
    String idCodeSuffix;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
