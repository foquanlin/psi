/*
 * 项目名称:platform-plus
 * 类名称:Login.java
 * 包名称:com.platform.modules.app.annotation
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author 李鹏军
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
