package com.tongyi.modules.wx.dao;

import com.tongyi.modules.wx.entity.WxAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公众号账号
 *
 */
@Mapper
@CacheNamespace(flushInterval = 300000L)//缓存五分钟过期
public interface WxAccountMapper extends BaseMapper<WxAccount> {
	
}
