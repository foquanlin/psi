package com.tongyi.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.common.utils.PageUtils;
import com.tongyi.modules.wx.entity.WxQrCode;
import com.tongyi.modules.wx.form.WxQrCodeForm;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import java.util.Map;

/**
 * 公众号带参二维码
 *
 * @author 林佛权
 */
public interface WxQrCodeService extends IService<WxQrCode> {
    /**
     * 分页查询用户数据
     * @param params 查询参数
     * @return PageUtils 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 创建公众号带参二维码
     *
     *
     * @param appid
     * @param form
     * @return
     */
    WxMpQrCodeTicket createQrCode(String appid, WxQrCodeForm form) throws WxErrorException;
}

