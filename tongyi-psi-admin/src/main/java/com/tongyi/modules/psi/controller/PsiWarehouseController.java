/*
 * 项目名称:项目名称
 * 类名称:PsiWarehouseController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiWarehouseEntity;
import com.tongyi.modules.psi.service.PsiWarehouseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 仓库Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RestController
@RequestMapping("psi/warehouse")
public class PsiWarehouseController extends AbstractController {
    @Autowired
    private PsiWarehouseService psiWarehouseService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:warehouse:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiWarehouseEntity> list = psiWarehouseService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询仓库
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:warehouse:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiWarehouseService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:warehouse:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiWarehouseEntity psiWarehouse = psiWarehouseService.getById(id);
        return RestResponse.success("info", psiWarehouse);
    }

    /**
     * 新增仓库
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增仓库")
    @PostMapping("/save")
    @RequiresPermissions("psi:warehouse:save")
    public RestResponse save(@RequestBody PsiWarehouseEntity entity) {
        psiWarehouseService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改仓库
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改仓库")
    @PostMapping("/update")
    @RequiresPermissions("psi:warehouse:update")
    public RestResponse update(@RequestBody PsiWarehouseEntity entity) {
        psiWarehouseService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除仓库
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除仓库")
    @PostMapping("/delete")
    @RequiresPermissions("psi:warehouse:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiWarehouseService.deleteBatch(ids);
        return RestResponse.success();
    }

    @SysLog("设置默认仓库")
    @GetMapping("/default")
    @RequiresPermissions("psi:warehouse:default")
    public RestResponse defaultWarehouse(@RequestParam("id") String id) {
        psiWarehouseService.defaultWarehouse(id);
        return RestResponse.success();
    }
    @SysLog("设置仓库状态")
    @GetMapping("/status")
    @RequiresPermissions("psi:warehouse:status")
    public RestResponse statusWarehouse(@RequestParam("id") String id,@RequestParam("status")String status) {
        psiWarehouseService.warehouseStatus(id,status);
        return RestResponse.success();
    }
}
