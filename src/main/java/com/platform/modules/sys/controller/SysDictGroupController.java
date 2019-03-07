/*
 * 项目名称:platform-plus
 * 类名称:SysDictGroupController.java
 * 包名称:com.platform.modules.sys.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.RestResponse;
import com.platform.common.validator.ValidatorUtils;
import com.platform.common.validator.group.AddGroup;
import com.platform.common.validator.group.UpdateGroup;
import com.platform.modules.sys.entity.SysDictGroupEntity;
import com.platform.modules.sys.service.SysDictGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典分组Controller
 *
 * @author 李鹏军
 * @date 2019-01-15 11:42:20
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
    @RequestMapping("/queryAll")
    @RequiresPermissions("sys:dictgroup:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<SysDictGroupEntity> list = sysDictGroupService.queryAll(params);

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
    public RestResponse list(@RequestParam Map<String, Object> params) {
        Page page = sysDictGroupService.queryPage(params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
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
    @RequestMapping("/save")
    @RequiresPermissions("sys:dictgroup:save")
    public RestResponse save(@RequestBody SysDictGroupEntity sysDictGroup) {
        ValidatorUtils.validateEntity(sysDictGroup, AddGroup.class);
        sysDictGroupService.add(sysDictGroup);

        return RestResponse.success();
    }

    /**
     * 修改数据字典分组
     *
     * @param sysDictGroup sysDictGroup
     * @return RestResponse
     */
    @SysLog("修改数据字典分组")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dictgroup:update")
    public RestResponse update(@RequestBody SysDictGroupEntity sysDictGroup) {
        ValidatorUtils.validateEntity(sysDictGroup, UpdateGroup.class);
        sysDictGroupService.update(sysDictGroup);

        return RestResponse.success();
    }

    /**
     * 删除数据字典分组
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除数据字典分组")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dictgroup:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysDictGroupService.deleteBatch(ids);

        return RestResponse.success();
    }
}
