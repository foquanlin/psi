/*
 * 项目名称:项目名称
 * 类名称:PsiCatalogController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiCatalogEntity;
import com.tongyi.modules.psi.service.PsiCatalogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 商品分类Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RestController
@RequestMapping("psi/catalog")
public class PsiCatalogController extends AbstractController {
    @Autowired
    private PsiCatalogService psiCatalogService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:catalog:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiCatalogEntity> list = psiCatalogService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询商品分类
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:catalog:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiCatalogService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:catalog:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiCatalogEntity psiCatalog = psiCatalogService.getById(id);
        return RestResponse.success("info", psiCatalog);
    }

    /**
     * 新增商品分类
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增商品分类")
    @RequestMapping("/save")
    @RequiresPermissions("psi:catalog:save")
    public RestResponse save(@RequestBody PsiCatalogEntity entity) {
        psiCatalogService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改商品分类
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改商品分类")
    @RequestMapping("/update")
    @RequiresPermissions("psi:catalog:update")
    public RestResponse update(@RequestBody PsiCatalogEntity entity) {
        psiCatalogService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除商品分类
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除商品分类")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:catalog:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiCatalogService.deleteBatch(ids);
        return RestResponse.success();
    }
}
