/*
 * 项目名称:进销存系统
 * 类名称:PsiBankbillController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiBankbillEntity;
import com.tongyi.modules.psi.service.PsiBankbillService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 银行账单Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 */
@RestController
@RequestMapping("psi/bankbill")
public class PsiBankbillController extends AbstractController {
    @Autowired
    private PsiBankbillService psiBankbillService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:bankbill:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiBankbillEntity> list = psiBankbillService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询银行账单
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:bankbill:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiBankbillService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:bankbill:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiBankbillEntity psiBankbill = psiBankbillService.getById(id);
        return RestResponse.success("info", psiBankbill);
    }

    /**
     * 新增银行账单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增银行账单")
    @PostMapping("/save")
    @RequiresPermissions("psi:bankbill:save")
    public RestResponse save(@RequestBody PsiBankbillEntity entity) {
        psiBankbillService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改银行账单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改银行账单")
    @PostMapping("/update")
    @RequiresPermissions("psi:bankbill:update")
    public RestResponse update(@RequestBody PsiBankbillEntity entity) {
        psiBankbillService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除银行账单
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除银行账单")
    @PostMapping("/delete")
    @RequiresPermissions("psi:bankbill:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiBankbillService.deleteBatch(ids);
        return RestResponse.success();
    }
}
