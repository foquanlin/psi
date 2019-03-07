/*
 * 项目名称:platform-plus
 * 类名称:ShiroUtils.java
 * 包名称:com.platform.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.utils;

import com.platform.common.exception.BusinessException;
import com.platform.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author 李鹏军
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new BusinessException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
