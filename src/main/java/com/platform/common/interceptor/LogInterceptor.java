/*
 * 项目名称:platform-plus
 * 类名称:LogInterceptor.java
 * 包名称:com.platform.common.interceptor
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/22 16:23    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.interceptor;

import com.platform.common.utils.IpUtils;
import com.platform.common.utils.RequestUtils;
import com.platform.common.utils.ShiroUtils;
import com.platform.modules.sys.entity.SysUserEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 日志输出拦截器
 *
 * @author 李鹏军
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = LogFactory.getLog(LogInterceptor.class);

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
     * preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("REQUEST_START_TIME", new Date());

        return true;

    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
     * postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
     * afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) {

        Date start = (Date) request.getAttribute("REQUEST_START_TIME");
        Date end = new Date();

        log.info("本次请求耗时：" + (end.getTime() - start.getTime()) + "毫秒；" + getRequestInfo(request).toString());

    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Object handler)
            throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /**
     * 主要功能:获取请求详细信息
     * 注意事项:无
     *
     * @param request 请求
     * @return 请求信息
     */
    private StringBuilder getRequestInfo(HttpServletRequest request) {
        StringBuilder reqInfo = new StringBuilder();
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String urlPath = urlPathHelper.getLookupPathForRequest(request);
        reqInfo.append(" 请求路径=").append(urlPath);
        reqInfo.append(" 来源IP=").append(IpUtils.getIpAddr(request));

        try {
            SysUserEntity sysUser = ShiroUtils.getUserEntity();
            if (sysUser != null) {
                String userName = (sysUser.getUserName());
                reqInfo.append(" 操作人=").append(userName);
            }
        } catch (Exception ignored) {

        }
        reqInfo.append(" 请求参数=").append(RequestUtils.getParameters(request).toString());
        return reqInfo;
    }
}
