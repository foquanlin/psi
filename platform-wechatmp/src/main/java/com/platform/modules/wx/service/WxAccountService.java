package com.platform.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.utils.PageUtils;
import com.platform.modules.wx.entity.WxAccount;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 公众号账号
 *
 * @author niefy
 * @date 2020-06-17 13:56:51
 */
public interface WxAccountService extends IService<WxAccount> {
    /**
     * 分页查询用户数据
     * @param params 查询参数
     * @return PageUtils 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    @Override
    boolean save(WxAccount entity);

    @Override
    boolean removeByIds(Collection<? extends Serializable> idList);
}

