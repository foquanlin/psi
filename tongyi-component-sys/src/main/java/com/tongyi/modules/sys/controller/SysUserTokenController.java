/*
 * 项目名称:tongyi-component
 * 类名称:SysUserTokenController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-02-01 11:12:49        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.service.SysUserTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统用户TokenController
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("sys/usertoken")
public class SysUserTokenController {
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:usertoken:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = sysUserTokenService.listPage(current,size,params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 批量下线用户(删除用户token记录)
     *
     * @param userIds userIds
     * @return RestResponse
     */
    @SysLog("批量下线用户")
    @RequestMapping("/offline")
    @RequiresPermissions("sys:usertoken:offline")
    public RestResponse offline(@RequestBody String[] userIds) {
        sysUserTokenService.offlineBatch(userIds);
        return RestResponse.success();
    }
}
