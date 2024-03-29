/*
 * 项目名称:项目名称
 * 类名称:PsiCheckController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import com.tongyi.modules.psi.service.execute.StockCheckExecute;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.service.PsiCheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.*;

/**
 * 盘点Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@RestController
@RequestMapping("psi/check")
public class PsiCheckController extends AbstractController {
    @Autowired
    private PsiCheckService psiCheckService;
    @Autowired
    private PsiCheckDetailService checkDetailService;
    @Autowired
    private StockCheckExecute stockCheckExecute;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:check:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiCheckEntity> list = psiCheckService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询盘点
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:check:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiCheckService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:check:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiCheckEntity psiCheck = psiCheckService.getById(id);
        Map<String,Object> params = new HashMap<>();
        params.put("cid",id);
        List<PsiCheckDetailEntity> list = checkDetailService.listAll(params);
        return RestResponse.success("info", psiCheck).put("list",list);
    }

    /**
     * 新增盘点
     * @return RestResponse
     */
    @SysLog("新增盘点")
    @PostMapping("/save")
    @RequiresPermissions("psi:check:save")
    public RestResponse save(@RequestBody String json) {
        JsonObject params = JsonParser.parseString(json).getAsJsonObject();
        String warehouseId = params.get("warehouseId").getAsString();
        String memo = params.get("memo").getAsString();
        params.addProperty("userId",getUserId());
        PsiCheckEntity entity = PsiCheckEntity.newEntity(getUserId(),warehouseId,memo);
        stockCheckExecute.execute(entity,params);
        return RestResponse.success();
    }

    /**
     * 修改盘点
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("修改盘点")
//    @PostMapping("/update")
//    @RequiresPermissions("psi:check:update")
//    public RestResponse update(@RequestBody PsiCheckEntity entity) {
//        psiCheckService.updateEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 根据主键删除盘点
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除盘点")
    @PostMapping("/delete")
    @RequiresPermissions("psi:check:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiCheckService.deleteBatch(ids);
        return RestResponse.success();
    }
}
