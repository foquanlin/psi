/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import com.tongyi.modules.psi.service.PsiGoodsSkuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 商品skuController
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 */
@RestController
@RequestMapping("psi/goodssku")
public class PsiGoodsSkuController extends AbstractController {
    @Autowired
    private PsiGoodsSkuService psiGoodsSkuService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
//    @RequiresPermissions("psi:goodssku:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiGoodsSkuEntity> list = psiGoodsSkuService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询商品sku
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:goods:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiGoodsSkuService.listPage(current,size,params);
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
        PsiGoodsSkuEntity psiGoodsSku = psiGoodsSkuService.getById(id);
        return RestResponse.success("info", psiGoodsSku);
    }

    /**
     * 新增商品sku
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增商品sku")
    @PostMapping("/save")
    @RequiresPermissions("psi:goods:save")
    public RestResponse save(@RequestBody PsiGoodsSkuEntity entity) {
        Map<String,Object> params = new HashMap<>();
        params.put("goodsId",entity.getGoodsId());
        params.put("specName",entity.getSpecName());
        List<PsiGoodsSkuEntity> skuList = psiGoodsSkuService.listAll(params);
        if (null != skuList && !skuList.isEmpty()){
            throw new BusinessException("此商品明细已存在");
        }
        entity.setStatus(PsiGoodsSkuEntity.Status.UP.getCode());
        psiGoodsSkuService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改商品sku
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改商品sku")
    @PostMapping("/update")
    @RequiresPermissions("psi:goods:update")
    public RestResponse update(@RequestBody PsiGoodsSkuEntity entity) {
        psiGoodsSkuService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除商品sku
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除商品sku")
    @PostMapping("/delete")
    @RequiresPermissions("psi:goods:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiGoodsSkuService.deleteBatch(ids);
        return RestResponse.success();
    }

    @SysLog("商品sku上下线")
    @GetMapping("/updown")
    @RequiresPermissions("psi:goods:updown")
    public RestResponse updown(@RequestParam("id")String id) {
        PsiGoodsSkuEntity sku = psiGoodsSkuService.getById(id);
        sku.reverseStatus();
        psiGoodsSkuService.updateEntity(sku);
        return RestResponse.success();
    }
}
