/*
 * 项目名称:项目名称
 * 类名称:PsiStockController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiStockService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 库存Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RestController
@RequestMapping("psi/stock")
public class PsiStockController extends AbstractController {
    @Autowired
    private PsiStockService psiStockService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:stock:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiStockEntity> list = psiStockService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询库存
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @PostMapping("/list")
    @RequiresPermissions("psi:stock:list")
    public RestResponse list(@RequestBody Map<String, Object> params) {
        Integer current = (Integer)params.get("page");
        Integer size = (Integer)params.get("limit");
        if (current == null){
            current =1;
        }
        if (size == null) {
            size = 10;
        }
        PageInfo page = psiStockService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:stock:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiStockEntity psiStock = psiStockService.getById(id);
        return RestResponse.success("info", psiStock);
    }

    /**
     * 新增库存
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增库存")
    @PostMapping("/save")
    @RequiresPermissions("psi:stock:save")
    public RestResponse save(@RequestBody PsiStockEntity entity) {
        entity.setCreateUid(getUserId());
        psiStockService.addEntity(entity);
        PsiStockEntity info = psiStockService.getById(entity.getId());
        return RestResponse.success().put("info",info);
    }

    /**
     * 修改库存
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改库存")
    @PostMapping("/update")
    @RequiresPermissions("psi:stock:update")
    public RestResponse update(@RequestBody PsiStockEntity entity) {
        psiStockService.updateEntity(entity);
        PsiStockEntity info = psiStockService.getById(entity.getId());
        return RestResponse.success().put("info",info);
    }

    /**
     * 根据主键删除库存
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除库存")
    @PostMapping("/delete")
    @RequiresPermissions("psi:stock:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiStockService.deleteEntity(ids);
        return RestResponse.success();
    }

    @SysLog("入库-库存调整")
    @PostMapping("/in")
    @RequiresPermissions("psi:stock:in")
    public RestResponse instock(@RequestBody PsiStockEntity entity) {
        entity.setCreateUid(this.getUserId());
        entity.setType(PsiStockEntity.Type.IN.getCode());
        entity.setCatalog(PsiStockEntity.Catalog.TIAOZHENG.getCode());
        psiStockService.addEntity(entity);
        return RestResponse.success();
    }
    @SysLog("出库-库存调整")
    @PostMapping("/out")
    @RequiresPermissions("psi:stock:out")
    public RestResponse outstock(@RequestBody PsiStockEntity entity) {
        entity.setCreateUid(this.getUserId());
        entity.setType(PsiStockEntity.Type.OUT.getCode());
        entity.setCatalog(PsiStockEntity.Catalog.TIAOZHENG.getCode());
        psiStockService.addEntity(entity);
        return RestResponse.success();
    }

    @SysLog("查询库存商品")
    @PostMapping("/skulist")
//    @RequiresPermissions("psi:stock:skulist")
    public RestResponse selectSkuList(@RequestBody Map<String, Object> params) {
        Integer current = (Integer)params.get("page");
        Integer size = (Integer)params.get("limit");
        if (current == null){
            current =1;
        }
        if (size == null) {
            size = 10;
        }
        PageInfo<PsiGoodsSkuEntity> page = psiStockService.selectSkuList(current,size,params);
        return RestResponse.success("page", page);
    }
}
