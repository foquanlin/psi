/*
 * 项目名称:platform-plus
 * 类名称:SysDictController.java
 * 包名称:com.platform.modules.sys.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.RestResponse;
import com.platform.common.validator.ValidatorUtils;
import com.platform.common.validator.group.AddGroup;
import com.platform.common.validator.group.UpdateGroup;
import com.platform.modules.sys.entity.SysDictEntity;
import com.platform.modules.sys.entity.SysDictGroupEntity;
import com.platform.modules.sys.service.SysDictGroupService;
import com.platform.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典Controller
 *
 * @author 李鹏军
 * @date 2019-01-15 11:42:20
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysDictGroupService sysDictGroupService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/queryAll")
    @RequiresPermissions("sys:dict:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<SysDictEntity> list = sysDictService.queryAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        IPage page = sysDictService.queryPage(params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public RestResponse info(@PathVariable("id") String id) {
        SysDictEntity sysDict = sysDictService.getById(id);

        return RestResponse.success().put("dict", sysDict);
    }

    /**
     * 保存
     *
     * @return RestResponse
     */
    @SysLog("保存数据字典")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public RestResponse save(@RequestBody SysDictEntity sysDict) {
        ValidatorUtils.validateEntity(sysDict, AddGroup.class);
        sysDictService.add(sysDict);

        return RestResponse.success();
    }

    /**
     * 修改
     *
     * @return RestResponse
     */
    @SysLog("修改数据字典")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public RestResponse update(@RequestBody SysDictEntity sysDict) {
        ValidatorUtils.validateEntity(sysDict, UpdateGroup.class);
        sysDictService.update(sysDict);

        return RestResponse.success();
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除数据字典")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysDictService.deleteBatch(ids);

        return RestResponse.success();
    }

    /**
     * 根据code查询数据字典
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/queryByCode")
    public RestResponse queryByCode(@RequestParam Map<String, Object> params) {
        String code = (String) params.get("code");
        SysDictGroupEntity sysDictGroupEntity = sysDictGroupService.getOne(new QueryWrapper<SysDictGroupEntity>()
                .eq(StringUtils.isNotBlank(code), "code", code)
        );
        String type = "";
        if (null != sysDictGroupEntity) {
            type = sysDictGroupEntity.getName();
        }

        List<SysDictEntity> list = sysDictService.queryByCode(params);

        return RestResponse.success().put("list", list).put("type", type);
    }
}
