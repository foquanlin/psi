/*
 * 项目名称:项目名称
 * 类名称:SysNationController.java
 * 包名称:com.tongyi.modules.sys.controller
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.entity.SysNationEntity;
import com.tongyi.modules.sys.service.SysNationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 民族Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 */
@RestController
@RequestMapping("sys/nation")
public class SysNationController extends AbstractController {
    @Autowired
    private SysNationService sysNationService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/queryAll")
    @RequiresPermissions("sys:nation:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<SysNationEntity> list = sysNationService.listAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 分页查询民族
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:nation:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = sysNationService.listPage(current, size, params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param code 主键
     * @return RestResponse
     */
    @GetMapping("/info/{code}")
    @RequiresPermissions("sys:nation:info")
    public RestResponse info(@PathVariable("code") String code) {
        SysNationEntity sysNation = sysNationService.getById(code);

        return RestResponse.success().put("nation", sysNation);
    }

    /**
     * 新增民族
     *
     * @param sysNation sysNation
     * @return RestResponse
     */
    @SysLog("新增民族")
    @PostMapping("/save")
    @RequiresPermissions("sys:nation:save")
    public RestResponse save(@RequestBody SysNationEntity sysNation) {

        sysNationService.addEntity(sysNation);

        return RestResponse.success();
    }

    /**
     * 修改民族
     *
     * @param sysNation sysNation
     * @return RestResponse
     */
    @SysLog("修改民族")
    @PostMapping("/update")
    @RequiresPermissions("sys:nation:update")
    public RestResponse update(@RequestBody SysNationEntity sysNation) {

        sysNationService.updateEntity(sysNation);

        return RestResponse.success();
    }

    /**
     * 根据主键删除民族
     *
     * @param codes codes
     * @return RestResponse
     */
    @SysLog("删除民族")
    @PostMapping("/delete")
    @RequiresPermissions("sys:nation:delete")
    public RestResponse delete(@RequestBody String[] codes) {
        sysNationService.deleteBatch(codes);

        return RestResponse.success();
    }
}
