/*
 * 项目名称:项目名称
 * 类名称:PsiCheckController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.service.PsiCheckService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:check:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
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
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:check:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiCheckEntity psiCheck = psiCheckService.getById(id);
        return RestResponse.success("info", psiCheck);
    }

    /**
     * 新增盘点
     * @return RestResponse
     */
    @SysLog("新增盘点")
    @RequestMapping("/save")
    @RequiresPermissions("psi:check:save")
    public RestResponse save(@RequestBody Map<String,Object> map) {
        String warehouseId = (String)map.get("warehouseId");
        String memo = (String)map.get("memo");
        List<Map<String,Object>> list  = (List)map.get("dataList");
        if (null == list || list.isEmpty()) {
            throw new BusinessException("请录入盘点明细");
        }
        List<PsiCheckDetailEntity> details = new ArrayList<>();
        list.forEach(item ->{
            details.add(PsiCheckDetailEntity.newEntity(item));
        });
        psiCheckService.addCheck(getUserId(),warehouseId,memo,details);
        return RestResponse.success();
    }

    /**
     * 修改盘点
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改盘点")
    @RequestMapping("/update")
    @RequiresPermissions("psi:check:update")
    public RestResponse update(@RequestBody PsiCheckEntity entity) {
        psiCheckService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除盘点
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除盘点")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:check:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiCheckService.deleteBatch(ids);
        return RestResponse.success();
    }
}
