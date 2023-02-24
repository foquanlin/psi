package com.tongyi.modules.app.entity;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 林佛权
 */
@Data
@ApiModel
public class FullUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * errMsg
     */
    private String errMsg;
    /**
     * rawData
     */
    private String rawData;
    /**
     * data(QQ)
     */
    private String data;
    /**
     * userInfo
     */
    private WxMaUserInfo userInfo;
    /**
     * encryptedData
     */
    private String encryptedData;
    /**
     * iv
     */
    private String iv;
    /**
     * signature
     */
    private String signature;
}
