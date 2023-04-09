/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationGoodsController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.service.PsiStockService;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 调拨单明细Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/allocationgoods")
public class PsiAllocationGoodsController extends AbstractController {
    @Autowired
    private PsiAllocationGoodsService psiAllocationGoodsService;
    @Autowired
    private PsiStockService stockService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
//    @RequestMapping("/listAll")
//    @RequiresPermissions("psi:allocationgoods:list")
//    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
//        List<PsiAllocationGoodsEntity> list = psiAllocationGoodsService.listAll(params);
//        return RestResponse.success("list", list);
//    }

    /**
     * 分页查询调拨单明细
     *
     * @param params 查询参数
     * @return RestResponse
     */
//    @GetMapping("/list")
//    @RequiresPermissions("psi:allocationgoods:list")
//    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
//        PageInfo page = psiAllocationGoodsService.listPage(current,size,params);
//        return RestResponse.success("page", page);
//    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
//    @RequestMapping("/info/{id}")
//    @RequiresPermissions("psi:allocationgoods:info")
//    public RestResponse info(@PathVariable("id") String id) {
//        PsiAllocationGoodsEntity psiAllocationGoods = psiAllocationGoodsService.getById(id);
//        return RestResponse.success("info", psiAllocationGoods);
//    }

    /**
     * 新增调拨单明细
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("新增调拨单明细")
//    @RequestMapping("/save")
//    @RequiresPermissions("psi:allocationgoods:save")
//    public RestResponse save(@RequestBody PsiAllocationGoodsEntity entity) {
//        psiAllocationGoodsService.addEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 修改调拨单明细
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("修改调拨单明细")
//    @RequestMapping("/update")
//    @RequiresPermissions("psi:allocationgoods:update")
//    public RestResponse update(@RequestBody PsiAllocationGoodsEntity entity) {
//        psiAllocationGoodsService.updateEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 根据主键删除调拨单明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除调拨单明细")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:allocationgoods:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiAllocationGoodsService.deleteEntity(ids);
        return RestResponse.success();
    }
}
