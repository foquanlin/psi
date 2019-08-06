package com.platform.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.platform.common.utils.Constant;
import com.platform.common.utils.JedisUtil;
import com.platform.common.utils.StringUtils;
import com.platform.modules.mall.entity.MallUserEntity;
import com.platform.modules.sys.entity.SysUserTokenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 司机经纬度存储
 *
 * @author Lipengjun
 */
@Slf4j
@Component
public class UserTokenCache {
    @Autowired
    private JedisUtil jedisUtil;
    static SimplePropertyPreFilter filter = new SimplePropertyPreFilter();

    /**
     * 存放user和token的关联关系
     */
    public static final String XCX_TOKEN_USER_PREFIXX = "xcx_token_user_";
    public static final String XCX_USER_TOKEN_PREFIXX = "xcx_user_token_";
    public static final String XCX_USER_INFO_PREFIXX = "xcx_user_info_";

    /**
     * 根据用户Id
     *
     * @param userId
     * @return
     */
    public SysUserTokenEntity getUserTokenByUserId(String userId) {
        String temp = jedisUtil.get(toUserTokenKey(userId));
        if (!org.springframework.util.StringUtils.isEmpty(temp)) {
            SysUserTokenEntity d = JSON.parseObject(temp, SysUserTokenEntity.class);
            return d;
        } else {
            return null;
        }
    }

    /**
     * 根据token
     *
     * @param token
     * @return
     */

    public SysUserTokenEntity getUserInfoByToken(String token) {
        String temp = jedisUtil.get(toTokenUserKey(token));
        if (StringUtils.isNullOrEmpty(temp)) {
            return null;
        }
        return getUserTokenByUserId(temp);
    }

    public void del(String userId) {
        jedisUtil.del(toUserTokenKey(userId));
    }

    /**
     * 放置用户Id和token关系
     *
     * @param SysUserTokenEntity
     */
    public void putUserToken(SysUserTokenEntity SysUserTokenEntity) {
        jedisUtil.set(toUserTokenKey(SysUserTokenEntity.getUserId()), fromTokenCacheString(SysUserTokenEntity), Constant.EXPIRE);
        jedisUtil.set(toTokenUserKey(SysUserTokenEntity.getToken()), SysUserTokenEntity.getUserId().toString(), Constant.EXPIRE);
    }

    private String fromTokenCacheString(SysUserTokenEntity d) {
        if (d == null) {
            return null;
        }
        return JSON.toJSONString(d, filter);
    }

    private String toUserTokenKey(String userId) {
        return XCX_TOKEN_USER_PREFIXX + userId;
    }

    private String toTokenUserKey(String token) {
        return XCX_USER_TOKEN_PREFIXX + token;
    }

    private String toUserInfoKey(String userId) {
        return XCX_USER_INFO_PREFIXX + userId;
    }

    public MallUserEntity getUserInfo(String userId) {
        log.error("getUserInfo key:" + toUserInfoKey(userId));
        String temp = jedisUtil.get(toUserInfoKey(userId));
        log.error("getUserInfo temp:" + temp);
        if (!org.springframework.util.StringUtils.isEmpty(temp)) {
            MallUserEntity d = JSON.parseObject(temp, MallUserEntity.class);
            return d;
        } else {
            return null;
        }
    }

    public void putUserInfo(MallUserEntity userEntity) {
        jedisUtil.set(toUserInfoKey(userEntity.getId()), fromUserCacheString(userEntity), Constant.EXPIRE);
    }

    private String fromUserCacheString(MallUserEntity d) {
        if (d == null) {
            return null;
        }
        return JSON.toJSONString(d, filter);
    }
}
