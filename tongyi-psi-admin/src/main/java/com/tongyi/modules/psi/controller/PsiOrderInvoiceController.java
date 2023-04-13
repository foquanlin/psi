/*
 * 项目名称:项目名称
 * 类名称:PsiOrderInvoiceController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderInvoiceEntity;
import com.tongyi.modules.psi.service.PsiOrderInvoiceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 订单发票Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/orderinvoice")
public class PsiOrderInvoiceController extends AbstractController {
    @Autowired
    private PsiOrderInvoiceService psiOrderInvoiceService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:orderinvoice:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderInvoiceEntity> list = psiOrderInvoiceService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询订单发票
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:orderinvoice:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderInvoiceService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:orderinvoice:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderInvoiceEntity psiOrderInvoice = psiOrderInvoiceService.getById(id);
        return RestResponse.success("info", psiOrderInvoice);
    }

    /**
     * 新增订单发票
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增订单发票")
    @PostMapping("/save")
    @RequiresPermissions("psi:orderinvoice:save")
    public RestResponse save(@RequestBody PsiOrderInvoiceEntity entity) {
        psiOrderInvoiceService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改订单发票
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改订单发票")
    @PostMapping("/update")
    @RequiresPermissions("psi:orderinvoice:update")
    public RestResponse update(@RequestBody PsiOrderInvoiceEntity entity) {
        psiOrderInvoiceService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除订单发票
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除订单发票")
    @PostMapping("/delete")
    @RequiresPermissions("psi:orderinvoice:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderInvoiceService.deleteBatch(ids);
        return RestResponse.success();
    }
}
