/*
 * 项目名称:项目名称
 * 类名称:PsiOrderDetailController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderDetailEntity;
import com.tongyi.modules.psi.service.PsiOrderDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 订单明细Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RestController
@RequestMapping("psi/orderdetail")
public class PsiOrderDetailController extends AbstractController {
    @Autowired
    private PsiOrderDetailService psiOrderDetailService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:orderdetail:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderDetailEntity> list = psiOrderDetailService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询订单明细
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:orderdetail:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderDetailService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:orderdetail:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderDetailEntity psiOrderDetail = psiOrderDetailService.getById(id);
        return RestResponse.success("info", psiOrderDetail);
    }

    /**
     * 新增订单明细
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增订单明细")
    @RequestMapping("/save")
    @RequiresPermissions("psi:orderdetail:save")
    public RestResponse save(@RequestBody PsiOrderDetailEntity entity) {
        psiOrderDetailService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改订单明细
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改订单明细")
    @RequestMapping("/update")
    @RequiresPermissions("psi:orderdetail:update")
    public RestResponse update(@RequestBody PsiOrderDetailEntity entity) {
        psiOrderDetailService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除订单明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除订单明细")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:orderdetail:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderDetailService.deleteBatch(ids);
        return RestResponse.success();
    }
}