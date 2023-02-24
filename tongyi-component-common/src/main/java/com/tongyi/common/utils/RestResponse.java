/*
 * 项目名称:tongyi-component
 * 类名称:R.java
 * 包名称:com.tongyi.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 *
 * @author 林佛权
 */
public class RestResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public RestResponse() {
        put("code", 0);
        put("msg", "success");
    }

    public static RestResponse error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static RestResponse error(String msg) {
        return error(500, msg);
    }

    public static RestResponse error(int code, String msg) {
        RestResponse rsp = new RestResponse();
        rsp.put("code", code);
        rsp.put("msg", msg);
        return rsp;
    }

    public static RestResponse success(String msg) {
        RestResponse rsp = new RestResponse();
        rsp.put("msg", msg);
        return rsp;
    }
    public static RestResponse success(Object msg) {
        RestResponse rsp = new RestResponse();
        rsp.put("data", msg);
        return rsp;
    }

    public static RestResponse success(Map<String, Object> map) {
        RestResponse rsp = new RestResponse();
        rsp.putAll(map);
        return rsp;
    }

    public static RestResponse success() {
        return new RestResponse();
    }
    public static RestResponse success(String key,Object obj) {
        return new RestResponse().put(key,obj);
    }
    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public RestResponse put(long total, long current,long size, List items) {
        super.put("total",total);
        super.put("current",current);
        super.put("items",items);
        super.put("size",size);
        return this;
    }
}
