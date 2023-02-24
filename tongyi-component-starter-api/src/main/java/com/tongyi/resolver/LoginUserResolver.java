/*
 * 项目名称:tongyi-component
 * 类名称:LoginUserHandlerMethodArgumentResolver.java
 * 包名称:com.tongyi.modules.app.resolver
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.resolver;

import com.tongyi.interceptor.AuthorizationInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author 林佛权
 */
//@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {
//    @Autowired
//    private IUserService<IUserEntity> userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return true;//parameter.getParameterType().isAssignableFrom(IUserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }

        //获取用户信息
        return null;
//        return userService.getById((String) object);
    }
}
