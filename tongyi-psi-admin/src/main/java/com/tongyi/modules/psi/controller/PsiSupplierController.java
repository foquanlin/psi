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
import org.apache.shiro.authz.annotation.Logical;
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
@RequestMapping(value = {"psi/supplier","psi/customer"})
public class PsiSupplierController extends AbstractController {
    @Autowired
    private PsiSupplierService psiSupplierService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions(value = {"psi:supplier:list","psi:customer:list"},logical = Logical.OR)
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
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
    @RequiresPermissions(value = {"psi:supplier:list","psi:customer:list"},logical = Logical.OR)
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
    @GetMapping("/info/{id}")
    @RequiresPermissions(value = {"psi:supplier:info","psi:customer:info"},logical = Logical.OR)
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
    @PostMapping("/save")
    @RequiresPermissions(value = {"psi:supplier:save","psi:customer:save"},logical = Logical.OR)
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
    @PostMapping("/update")
    @RequiresPermissions(value = {"psi:supplier:update","psi:customer:update"},logical = Logical.OR)
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
    @PostMapping("/delete")
    @RequiresPermissions(value = {"psi:supplier:delete","psi:customer:delete"},logical = Logical.OR)
    public RestResponse delete(@RequestBody String[] ids) {
        psiSupplierService.deleteBatch(ids);
        return RestResponse.success();
    }

    @SysLog("设置供应商状态")
    @GetMapping("/status")
    @RequiresPermissions(value = {"psi:supplier:status","psi:customer:status"})
    public RestResponse statusWarehouse(@RequestParam("id") String id,@RequestParam("status")String status) {
        psiSupplierService.supplierStatus(id,status);
        return RestResponse.success();
    }
}
