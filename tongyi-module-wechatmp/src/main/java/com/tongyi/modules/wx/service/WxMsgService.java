package com.tongyi.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.common.utils.PageUtils;
import com.tongyi.modules.wx.entity.WxMsg;

import java.util.Map;

/**
 * 微信消息
 *
 * @author 林佛权
 */
public interface WxMsgService extends IService<WxMsg> {
    /**
     * 分页查询用户数据
     * @param params 查询参数
     * @return PageUtils 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 记录msg，异步入库
     * @param msg
     */
    void addWxMsg(WxMsg msg);
}

