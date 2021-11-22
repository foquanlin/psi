/*
 * 项目名称:tongyi-component
 * 类名称:SysCacheController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/28 17:05    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.Constant;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.entity.SysCacheEntity;
import com.tongyi.modules.sys.service.SysCacheService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统缓存管理
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/sys/cache")
public class SysCacheController {
    public static final String STR_ZERO = "0";
    public static final String STR_ONE = "1";
    public static final String STR_TWO = "2";
    public static final String STR_THREE = "3";
    public static final String STR_FOUR = "4";
    /**
     * 权限前缀
     */
    public static final String SESSION = "SESSION:";

    /**
     * 系统缓存前缀
     */
    public static final String SYS_CACHE = "SYS_CACHE:";

    /**
     * 业务系统缓存前缀
     */
    public static final String MTM_CACHE = "MTM_CACHE:";

    @Autowired
    SysCacheService sysCacheService;

    /**
     * 查询缓存
     *
     * @param params 查询参数
     * @return
     */
    @RequiresPermissions("sys:cache:queryAll")
    @RequestMapping("/queryAll")
    public RestResponse queryAll(@RequestParam Map<String, String> params) {
        String type = params.get("type");
        if (STR_ONE.equals(type)) {
            //查询所有缓存
            params.put("pattern", "*");
        } else if (STR_TWO.equals(type)) {
            //查询session缓存
            params.put("pattern", SESSION + "*");
        } else if (STR_THREE.equals(type)) {
            //查询系统缓存
            params.put("pattern", SYS_CACHE + "*");
        } else if (STR_FOUR.equals(type)) {
            //查询业务缓存
            params.put("pattern", MTM_CACHE + "*");
        }
        List<SysCacheEntity> list = sysCacheService.queryAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 删除cache
     *
     * @param keys keys
     * @return RestResponse
     */
    @SysLog("删除redis缓存")
    @RequiresPermissions("sys:cache:deleteCache")
    @RequestMapping("/deleteCache")
    public RestResponse deleteBatch(@RequestBody String[] keys) {
        sysCacheService.deleteBatch(keys);

        return RestResponse.success();
    }
}
