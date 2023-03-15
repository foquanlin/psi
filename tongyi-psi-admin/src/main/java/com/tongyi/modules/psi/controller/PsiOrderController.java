/*
 * 项目名称:项目名称
 * 类名称:PsiOrderController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiOrderAmountEntity;
import com.tongyi.modules.psi.entity.PsiOrderDetailEntity;
import com.tongyi.modules.psi.service.*;
import com.tongyi.modules.psi.service.execute.BuyOrderCreateExecute;
import com.tongyi.modules.psi.service.execute.BuyOrderUpdateExecute;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购单Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RestController
@RequestMapping("psi/order")
public class PsiOrderController extends AbstractController {
    @Autowired
    private PsiOrderService psiOrderService;
    @Autowired
    private PsiOrderDetailService orderDetailService;
    @Autowired
    private BuyOrderCreateExecute buyOrderCreateExecute;
    @Autowired
    private BuyOrderUpdateExecute buyOrderUpdateExecute;

    @Autowired
    private PsiOrderAmountService orderAmountService;
    @Autowired
    private PsiOrderInvoiceService orderInvoiceService;

    @Autowired
    private PsiOrderExpressService orderExpressService;
    @Autowired
    private PsiOrderOperationService orderOperationService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions(value={"psi:order:list","psi:buyorder:list","psi:saleorder:list"},logical = Logical.OR)
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderEntity> list = psiOrderService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询采购单
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions(value = {"psi:order:list","psi:buyorder:list","psi:saleorder:list"},logical = Logical.OR)
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions(value={"psi:order:info","psi:buyorder:info","psi:saleorder:info"},logical = Logical.OR)
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderEntity psiOrder = psiOrderService.getById(id);
        Map<String,Object> params = new HashMap<>();
        params.put("orderId",psiOrder.getId());
        List<PsiOrderDetailEntity> details = orderDetailService.listAll(params);
        psiOrder.setDetails(details);
        List<PsiOrderAmountEntity> accountList = orderAmountService.listAll(params);
        return RestResponse.success("info", psiOrder).put("accountList",accountList);
    }

    /**
     * 新增采购单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增采购单")
    @RequestMapping("/save")
    @RequiresPermissions(value={"psi:order:save","psi:buyorder:save","psi:saleorder:save"},logical = Logical.OR)
    public RestResponse save(@RequestBody PsiOrderEntity entity) {
        psiOrderService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改采购单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改采购单")
    @RequestMapping("/update")
    @RequiresPermissions(value={"psi:order:update","psi:buyorder:update","psi:saleorder:update"},logical = Logical.OR)
    public RestResponse update(@RequestBody PsiOrderEntity entity) {
        psiOrderService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除采购单
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除采购单")
    @RequestMapping("/delete")
    @RequiresPermissions(value={"psi:order:delete","psi:buyorder:delete","psi:saleorder:delete"},logical = Logical.OR)
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderService.deleteBatch(ids);
        return RestResponse.success();
    }


    /**
     * 新增采购单
     *
     * @return RestResponse
     */
    @SysLog("新增采购单")
    @RequestMapping("/buyorder")
    @RequiresPermissions(value={"psi:order:save","psi:buyorder:save","psi:saleorder:save"},logical = Logical.OR)
    public RestResponse save(@RequestBody String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String createUid = getUserId();
        jsonObject.addProperty("userId",createUid); //填写制单人
        PsiOrderEntity entity = PsiOrderEntity.newBuyOrder(PsiOrderEntity.Type.ORDER);
        buyOrderCreateExecute.apply(entity,jsonObject);
        return RestResponse.success();
    }

    /**
     * 修改采购单
     * @param json
     * @return
     */
    @SysLog("修改采购单")
    @RequestMapping("/buyorderupdate")
    @RequiresPermissions(value={"psi:order:update","psi:buyorder:update","psi:saleorder:update"},logical = Logical.OR)
    public RestResponse updateBuyOrder(@RequestBody String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String createUid = getUserId();
        jsonObject.addProperty("userId",createUid); //填写制单人

        String orderId = jsonObject.get("id").getAsString();
        PsiOrderEntity entity = psiOrderService.getById(orderId);
        buyOrderUpdateExecute.apply(entity,jsonObject);
        return RestResponse.success();
    }
    @SysLog("修改发票状态")
    @RequestMapping("/invoiceStatus")
    @RequiresPermissions(value={"psi:order:update","psi:buyorder:update","psi:saleorder:update"},logical = Logical.OR)
    public RestResponse invoiceStatus(@RequestParam("id") String orderId,@RequestParam("invoiceStatus")String invoiceStatus) {
        PsiOrderEntity order = psiOrderService.getById(orderId);
        order.setInvoiceStatus(invoiceStatus);
        psiOrderService.updateEntity(order);
        return RestResponse.success();
    }
}
