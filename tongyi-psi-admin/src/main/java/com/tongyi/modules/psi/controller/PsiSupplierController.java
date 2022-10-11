/*
 * 项目名称:项目名称
 * 类名称:PsiSupplierController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiSupplierEntity;
import com.tongyi.modules.psi.service.PsiSupplierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 供应商Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RestController
@RequestMapping("psi/supplier")
public class PsiSupplierController extends AbstractController {
    @Autowired
    private PsiSupplierService psiSupplierService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:supplier:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiSupplierEntity> list = psiSupplierService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询供应商
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:supplier:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiSupplierService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:supplier:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiSupplierEntity psiSupplier = psiSupplierService.getById(id);
        return RestResponse.success("info", psiSupplier);
    }

    /**
     * 新增供应商
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增供应商")
    @RequestMapping("/save")
    @RequiresPermissions("psi:supplier:save")
    public RestResponse save(@RequestBody PsiSupplierEntity entity) {
        psiSupplierService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改供应商
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改供应商")
    @RequestMapping("/update")
    @RequiresPermissions("psi:supplier:update")
    public RestResponse update(@RequestBody PsiSupplierEntity entity) {
        psiSupplierService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除供应商
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除供应商")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:supplier:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiSupplierService.deleteBatch(ids);
        return RestResponse.success();
    }
}
