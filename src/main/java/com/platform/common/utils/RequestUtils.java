/*
 * 项目名称:platform-plus
 * 类名称:RequestUtils.java
 * 包名称:com.platform.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/22 16:30    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * HTTP请求处理工具类
 *
 * @author 李鹏军
 */
public class RequestUtils {
    private static final Logger LOGGER = Logger.getLogger(RequestUtils.class.getName());

    /**
     * 将request查询参数封装至Map
     *
     * @param request  请求
     * @param printLog 是否打印日志
     * @return 参数Map
     */
    public static Map<String, Object> getParameters(HttpServletRequest request,
                                                    boolean printLog) {
        Enumeration<String> enume = request.getParameterNames();
        //这里一般参数都在5个以内，所以使用默认的容量16
        Map<String, Object> map = new HashMap<>(16);
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
            if (printLog) {
                LOGGER.info(key + "==>" + value);
            }
        }
        return map;
    }

    /**
     * 将request查询参数封装至Map
     *
     * @param request 请求
     * @return 参数Map
     */
    public static Map<String, Object> getParameters(HttpServletRequest request) {

        return getParameters(request, false);
    }
}
