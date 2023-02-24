/*
 * 项目名称:tongyi-component
 * 类名称:SysSmsLogService.java
 * 包名称:com.tongyi.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-02-12 09:51:15        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.service;

import com.tongyi.core.IService;
import com.tongyi.modules.sys.entity.SysSmsLogEntity;

/**
 * 短信发送日志Service接口
 *
 * @author 林佛权
 */
public interface SysSmsLogService extends IService<SysSmsLogEntity> {

    /**
     * 发送短信
     *
     * @param smsLog smsLog
     * @return SysSmsLogEntity
     */
    SysSmsLogEntity sendSms(String userId, SysSmsLogEntity smsLog);
}
