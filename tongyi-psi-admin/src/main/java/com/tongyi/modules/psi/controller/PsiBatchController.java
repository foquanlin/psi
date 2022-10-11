/*
 * 项目名称:项目名称
 * 类名称:PsiBatchController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiBatchEntity;
import com.tongyi.modules.psi.service.PsiBatchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 批次Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@RestController
@RequestMapping("psi/batch")
public class PsiBatchController extends AbstractController {
    @Autowired
    private PsiBatchService psiBatchService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:batch:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiBatchEntity> list = psiBatchService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询批次
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:batch:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiBatchService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:batch:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiBatchEntity psiBatch = psiBatchService.getById(id);
        return RestResponse.success("info", psiBatch);
    }

    /**
     * 新增批次
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增批次")
    @RequestMapping("/save")
    @RequiresPermissions("psi:batch:save")
    public RestResponse save(@RequestBody PsiBatchEntity entity) {
        psiBatchService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改批次
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改批次")
    @RequestMapping("/update")
    @RequiresPermissions("psi:batch:update")
    public RestResponse update(@RequestBody PsiBatchEntity entity) {
        psiBatchService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除批次
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除批次")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:batch:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiBatchService.deleteBatch(ids);
        return RestResponse.success();
    }
}
