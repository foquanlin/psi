/*
 * 项目名称:platform-plus
 * 类名称:SysLoginForm.java
 * 包名称:com.platform.modules.sys.form
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.modules.sys.form;

import lombok.Data;

/**
 * 登录表单
 *
 * @author 林佛权
 */
@Data
public class SysLoginForm {
    private String userName;
    private String password;
    private String captcha;
    private String uuid;
}
