/*
 * 项目名称:platform-plus
 * 类名称:SysCaptchaEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统验证码
 *
 * @author 林佛权
 */
@Data
@TableName("SYS_CAPTCHA")
public class SysCaptchaEntity {
    @TableId
    private String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private Date expireTime;
}
