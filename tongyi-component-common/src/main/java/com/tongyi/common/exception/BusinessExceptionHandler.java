/*
 * 项目名称:tongyi-component
 * 类名称:BusinessExceptionHandler.java
 * 包名称:com.tongyi.common.exception
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.common.exception;

import com.google.gson.Gson;
import com.tongyi.common.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;

/**
 * 异常处理器
 *
 * @author 林佛权
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResponse methodNotSupportedHandler(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("url:{} -> 请求方式不正确:{},method:{}", request == null ? null : request.getRequestURL(),request == null ? null :request.getMethod(), e);
        return RestResponse.error(500, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResponse paramErrorHandler(Exception e, HttpServletRequest request) {
        log.error("url:{} -> 参数异常:{}", request == null ? null : request.getRequestURL(), e);
        return RestResponse.error(500, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RestResponse mismatchErrorHandler(MethodArgumentTypeMismatchException e) {
        log.error("参数转换失败，方法：{},参数：{}，信息：{}" ,Objects.requireNonNull(e.getParameter().getMethod()).getName() ,e.getName() , e.getMessage());
        return RestResponse.error(500, e.getMessage());
    }

    /**
     * 配合Assert使用，香
     *
     * @param e       e
     * @param request request
     * @return object
     */
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public RestResponse handler(RuntimeException e, HttpServletRequest request) {
        log.error("url:{} -> 业务异常:{}", request == null ? null : request.getRequestURL(), e);
        return RestResponse.error(500, e.getMessage());
    }

    @ExceptionHandler
    public RestResponse handler(Exception e, HttpServletRequest request) {
        log.error("url:{} -> 错误:{}", request == null ? null : request.getRequestURL(), e);
        Optional.ofNullable(request)
            .ifPresent(r -> {
//                TreeMap<String, String> reqMap = new TreeMap<>();
//                for (Map.Entry<String, String[]> me : r.getParameterMap().entrySet()) {
//                    String key = me.getKey();
//                    String value = me.getValue()[0];
//                    reqMap.put(key, value);
//                }
                log.error("url:{} -> 错误:{},参数:{}", request == null ? null : request.getRequestURL(), e,new Gson().toJson(r.getParameterMap()));
                }
            );
        return RestResponse.error(500, "服务器异常");
    }

    /**
     * body参数
     *
     * @param e BindException
     */
    @ExceptionHandler(BindException.class)
    public RestResponse handlerBindException(BindException e) {
        StringBuilder msg = new StringBuilder();
        FieldError c = e.getBindingResult().getFieldErrors().get(0);
        msg.append(c.getDefaultMessage()).append(":").append(c.getRejectedValue());
        return RestResponse.error(500, msg.toString());
    }

    /**
     * 方法参数
     *
     * @param e ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse handlerConstraintViolationException(ConstraintViolationException e) {
        StringBuilder msg = new StringBuilder();
        ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
        msg.append(violation.getMessage()).append(":").append(violation.getInvalidValue());
        return RestResponse.error(500, msg.toString());
    }
}
