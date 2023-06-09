/*
 * 项目名称:tongyi-component
 * 类名称:SysConfigController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.common.validator.ValidatorUtils;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.entity.SysConfigEntity;
import com.tongyi.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有系统配置列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = sysConfigService.listPage(current,size,params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public RestResponse info(@PathVariable("id") String id) {
        SysConfigEntity config = sysConfigService.getById(id);

        return RestResponse.success().put("config", config);
    }

    /**
     * 保存系统配置
     *
     * @param config config
     * @return RestResponse
     */
    @SysLog("保存系统配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public RestResponse save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        config.setStatus(1);

        sysConfigService.addEntity(config);

        return RestResponse.success();
    }

    /**
     * 修改系统配置
     *
     * @param config config
     * @return RestResponse
     */
    @SysLog("修改系统配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public RestResponse update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        config.setStatus(1);

        sysConfigService.updateEntity(config);

        return RestResponse.success();
    }

    /**
     * 删除系统配置
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除系统配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysConfigService.deleteBatch(ids);

        return RestResponse.success();
    }

}
