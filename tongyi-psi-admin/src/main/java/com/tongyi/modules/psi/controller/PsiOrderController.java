/*
 * 项目名称:项目名称
 * 类名称:PsiOrderController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import com.tongyi.modules.psi.service.PsiOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
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

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:order:list")
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
    @RequiresPermissions("psi:order:list")
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
    @RequiresPermissions("psi:order:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderEntity psiOrder = psiOrderService.getById(id);
        return RestResponse.success("info", psiOrder);
    }

    /**
     * 新增采购单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增采购单")
    @RequestMapping("/save")
    @RequiresPermissions("psi:order:save")
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
    @RequiresPermissions("psi:order:update")
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
    @RequiresPermissions("psi:order:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderService.deleteBatch(ids);
        return RestResponse.success();
    }
}