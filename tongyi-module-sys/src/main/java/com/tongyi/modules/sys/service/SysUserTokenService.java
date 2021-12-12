/*
 * 项目名称:tongyi-component
 * 类名称:SysUserTokenService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author 林佛权
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户Id
     * @return Map
     */
    String createToken(String userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户Id
     */
    void logout(String userId);

    /**
     * 批量下线用户(删除用户token记录)
     *
     * @param userIds 用户Ids
     */
    void offlineBatch(String[] userIds);
}
