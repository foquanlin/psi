/*
 * 项目名称:tongyi-component
 * 类名称:SysDictGroupController.java
 * 包名称:com.tongyi.modules.sys.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        林佛权     初版做成
 *
 * Copyright (c) 2018-2019 酷天科技
 */
package com.tongyi.modules.sys.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.common.validator.ValidatorUtils;
import com.tongyi.common.validator.group.AddGroup;
import com.tongyi.common.validator.group.UpdateGroup;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.sys.entity.SysDictGroupEntity;
import com.tongyi.modules.sys.service.SysDictGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典分组Controller
 *
 * @author 林佛权
 */
@RestController
@RequestMapping("sys/dictgroup")
public class SysDictGroupController {
    @Autowired
    private SysDictGroupService sysDictGroupService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("sys:dictgroup:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<SysDictGroupEntity> list = sysDictGroupService.listAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dictgroup:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = sysDictGroupService.listPage(current, size, params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dictgroup:info")
    public RestResponse info(@PathVariable("id") String id) {
        SysDictGroupEntity sysDictGroup = sysDictGroupService.getById(id);

        return RestResponse.success().put("dictgroup", sysDictGroup);
    }

    /**
     * 保存数据字典分组
     *
     * @param sysDictGroup sysDictGroup
     * @return RestResponse
     */
    @SysLog("保存数据字典分组")
    @PostMapping("/save")
    @RequiresPermissions("sys:dictgroup:save")
    public RestResponse save(@RequestBody SysDictGroupEntity sysDictGroup) {
        ValidatorUtils.validateEntity(sysDictGroup, AddGroup.class);
        sysDictGroupService.addEntity(sysDictGroup);

        return RestResponse.success();
    }

    /**
     * 修改数据字典分组
     *
     * @param sysDictGroup sysDictGroup
     * @return RestResponse
     */
    @SysLog("修改数据字典分组")
    @PostMapping("/update")
    @RequiresPermissions("sys:dictgroup:update")
    public RestResponse update(@RequestBody SysDictGroupEntity sysDictGroup) {
        ValidatorUtils.validateEntity(sysDictGroup, UpdateGroup.class);
        sysDictGroupService.updateEntity(sysDictGroup);

        return RestResponse.success();
    }

    /**
     * 删除数据字典分组
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除数据字典分组")
    @PostMapping("/delete")
    @RequiresPermissions("sys:dictgroup:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysDictGroupService.deleteBatch(ids);

        return RestResponse.success();
    }
}
