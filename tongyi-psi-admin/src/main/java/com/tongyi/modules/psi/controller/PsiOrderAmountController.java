/*
 * 项目名称:项目名称
 * 类名称:PsiOrderAmountController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderAmountEntity;
import com.tongyi.modules.psi.service.PsiOrderAmountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 订单账目Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/orderamount")
public class PsiOrderAmountController extends AbstractController {
    @Autowired
    private PsiOrderAmountService psiOrderAmountService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:orderamount:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderAmountEntity> list = psiOrderAmountService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询订单账目
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:orderamount:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderAmountService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:orderamount:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderAmountEntity psiOrderAmount = psiOrderAmountService.getById(id);
        return RestResponse.success("info", psiOrderAmount);
    }

    /**
     * 新增订单账目
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增订单账目")
    @RequestMapping("/save")
    @RequiresPermissions("psi:orderamount:save")
    public RestResponse save(@RequestBody PsiOrderAmountEntity entity) {
        psiOrderAmountService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改订单账目
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改订单账目")
    @RequestMapping("/update")
    @RequiresPermissions("psi:orderamount:update")
    public RestResponse update(@RequestBody PsiOrderAmountEntity entity) {
        psiOrderAmountService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除订单账目
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除订单账目")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:orderamount:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderAmountService.deleteBatch(ids);
        return RestResponse.success();
    }
}
