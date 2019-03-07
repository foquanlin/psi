/*
 * 项目名称:platform-plus
 * 类名称:BusinessExceptionHandler.java
 * 包名称:com.platform.common.exception
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.exception;

import com.platform.common.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author 李鹏军
 */
@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResponse handleBusinessException(BusinessException e) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("code", e.getCode());
        restResponse.put("msg", e.getMessage());

        return restResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResponse handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResponse.error(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RestResponse handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return RestResponse.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public RestResponse handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return RestResponse.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public RestResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResponse.error();
    }
}
