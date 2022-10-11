/*
 * 项目名称:项目名称
 * 类名称:PsiStockRecordController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiStockRecordEntity;
import com.tongyi.modules.psi.service.PsiStockRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 库存流水Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@RestController
@RequestMapping("psi/stockrecord")
public class PsiStockRecordController extends AbstractController {
    @Autowired
    private PsiStockRecordService psiStockRecordService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:stockrecord:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiStockRecordEntity> list = psiStockRecordService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询库存流水
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:stockrecord:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiStockRecordService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:stockrecord:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiStockRecordEntity psiStockRecord = psiStockRecordService.getById(id);
        return RestResponse.success("info", psiStockRecord);
    }

    /**
     * 新增库存流水
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增库存流水")
    @RequestMapping("/save")
    @RequiresPermissions("psi:stockrecord:save")
    public RestResponse save(@RequestBody PsiStockRecordEntity entity) {
        psiStockRecordService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改库存流水
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改库存流水")
    @RequestMapping("/update")
    @RequiresPermissions("psi:stockrecord:update")
    public RestResponse update(@RequestBody PsiStockRecordEntity entity) {
        psiStockRecordService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除库存流水
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除库存流水")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:stockrecord:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiStockRecordService.deleteBatch(ids);
        return RestResponse.success();
    }
}
