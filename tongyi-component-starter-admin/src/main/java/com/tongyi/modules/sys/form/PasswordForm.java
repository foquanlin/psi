/*
 * 项目名称:tongyi-component
 * 类名称:PasswordForm.java
 * 包名称:com.tongyi.modules.sys.form
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.form;

import lombok.Data;

/**
 * 密码表单
 *
 * @author 林佛权
 */
@Data
public class PasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
}
