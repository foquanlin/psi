package com.tongyi.modules.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.tongyi.modules.wx.form.WxQrCodeForm;
import lombok.Data;

/**
 * 公众号带参二维码
 *
 * @author 林佛权
 */
@Data
@TableName("wx_qr_code")
public class WxQrCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String appid;
    /**
     * 二维码类型
     */
    private Boolean isTemp;
    /**
     * 场景值ID
     */
    private String sceneStr;
    /**
     * 二维码ticket
     */
    private String ticket;
    /**
     * 二维码图片解析后的地址
     */
    private String url;
    /**
     * 该二维码失效时间
     */
    private Date expireTime;
    /**
     * 该二维码创建时间
     */
    private Date createTime;

    public WxQrCode() {
    }

    public WxQrCode(WxQrCodeForm form,String appid) {
        this.appid = appid;
        this.isTemp = form.getIsTemp();
        this.sceneStr = form.getSceneStr();
        this.createTime = new Date();
    }
}
