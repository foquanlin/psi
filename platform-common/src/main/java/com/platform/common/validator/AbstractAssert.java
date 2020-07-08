/*
 * 项目名称:platform-plus
 * 类名称:Assert.java
 * 包名称:com.platform.common.validator
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.platform.common.validator;

import com.platform.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author 林佛权
 */
public abstract class AbstractAssert {
    public static void exists(Object object, String message) {
        if (null!=object) {
            throw new BusinessException(message);
        }
    }
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
}
