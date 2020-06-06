package com.platform.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解  限流
 *
 * @author 林佛权
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLimit {
    String description() default "";
}
