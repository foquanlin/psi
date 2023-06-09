/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.google.gson.*;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import com.tongyi.modules.psi.service.PsiStockService;
import com.tongyi.modules.psi.service.execute.StockAllocationExecute;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiAllocationEntity;
import com.tongyi.modules.psi.service.PsiAllocationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调拨单Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RestController
@RequestMapping("psi/allocation")
public class PsiAllocationController extends AbstractController {
    @Autowired
    private PsiAllocationService psiAllocationService;
    @Autowired
    private PsiAllocationGoodsService allocationGoodsService;
    @Autowired
    private StockAllocationExecute allocationExecute;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:allocation:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiAllocationEntity> list = psiAllocationService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询调拨单
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:allocation:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiAllocationService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:allocation:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiAllocationEntity psiAllocation = psiAllocationService.getById(id);
        Map<String,Object> params = new HashMap<>();
        params.put("allocationId",id);
        List<PsiAllocationGoodsEntity> list = allocationGoodsService.listAll(params);
        return RestResponse.success("info", psiAllocation).put("goodsList",list);
    }

    /**
     * 新增调拨单
     *
     * @return RestResponse
     */
    @SysLog("新增调拨单")
    @PostMapping("/save")
    @RequiresPermissions("psi:allocation:save")
    public RestResponse save(@RequestBody String body) {
        JsonObject map = JsonParser.parseString(body).getAsJsonObject();
        String inWarehouseId = map.get("inWarehouseId").getAsString();
        String outWarehouseId = map.get("outWarehouseId").getAsString();
        String memo = map.get("memo").getAsString();
        PsiAllocationEntity entity = PsiAllocationEntity.newEntity(inWarehouseId,outWarehouseId,memo);
        allocationExecute.apply(entity,map);
        return RestResponse.success();
    }

    /**
     * 修改调拨单
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("修改调拨单")
//    @PostMapping("/update")
//    @RequiresPermissions("psi:allocation:update")
//    public RestResponse update(@RequestBody PsiAllocationEntity entity) {
//        psiAllocationService.updateEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 根据主键删除调拨单
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除调拨单")
    @PostMapping("/delete")
    @RequiresPermissions("psi:allocation:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiAllocationService.deleteBatch(ids);
        return RestResponse.success();
    }
}
