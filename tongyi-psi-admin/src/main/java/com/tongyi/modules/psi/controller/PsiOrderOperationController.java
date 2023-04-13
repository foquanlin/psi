/*
 * 项目名称:项目名称
 * 类名称:PsiOrderOperationController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderOperationEntity;
import com.tongyi.modules.psi.service.PsiOrderOperationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 订单操作日志Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 */
@RestController
@RequestMapping("psi/orderoperation")
public class PsiOrderOperationController extends AbstractController {
    @Autowired
    private PsiOrderOperationService psiOrderOperationService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:orderoperation:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderOperationEntity> list = psiOrderOperationService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询订单操作日志
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:orderoperation:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderOperationService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:orderoperation:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderOperationEntity psiOrderOperation = psiOrderOperationService.getById(id);
        return RestResponse.success("info", psiOrderOperation);
    }

    /**
     * 新增订单操作日志
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增订单操作日志")
    @PostMapping("/save")
    @RequiresPermissions("psi:orderoperation:save")
    public RestResponse save(@RequestBody PsiOrderOperationEntity entity) {
        psiOrderOperationService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改订单操作日志
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改订单操作日志")
    @PostMapping("/update")
    @RequiresPermissions("psi:orderoperation:update")
    public RestResponse update(@RequestBody PsiOrderOperationEntity entity) {
        psiOrderOperationService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除订单操作日志
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除订单操作日志")
    @PostMapping("/delete")
    @RequiresPermissions("psi:orderoperation:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderOperationService.deleteBatch(ids);
        return RestResponse.success();
    }
}
