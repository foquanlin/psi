/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSpecController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.psi.service.PsiGoodsSkuService;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiGoodsSpecEntity;
import com.tongyi.modules.psi.service.PsiGoodsSpecService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:27
 */
@RestController
@RequestMapping("psi/goodsspec")
public class PsiGoodsSpecController extends AbstractController {
    @Autowired
    private PsiGoodsSpecService psiGoodsSpecService;
    @Autowired
    private PsiGoodsSkuService skuService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:goods:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiGoodsSpecEntity> list = psiGoodsSpecService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:goods:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiGoodsSpecService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:goods:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiGoodsSpecEntity psiGoodsSpec = psiGoodsSpecService.getById(id);
        return RestResponse.success("info", psiGoodsSpec);
    }

    /**
     * 新增
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增")
    @PostMapping("/save")
    @RequiresPermissions("psi:goods:save")
    public RestResponse save(@RequestBody PsiGoodsSpecEntity entity) {
        psiGoodsSpecService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改")
    @PostMapping("/update")
    @RequiresPermissions("psi:goods:update")
    public RestResponse update(@RequestBody PsiGoodsSpecEntity entity) {
        psiGoodsSpecService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除")
    @PostMapping("/delete")
    @RequiresPermissions("psi:goods:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiGoodsSpecService.deleteBatch(ids);
        return RestResponse.success();
    }
}
