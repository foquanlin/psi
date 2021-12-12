package com.tongyi.modules.wx.form;

import com.tongyi.common.utils.JsonUtils;
import lombok.Data;

@Data
public class MaterialFileDeleteForm {
    String mediaId;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
