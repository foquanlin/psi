/*
 * 项目名称:项目名称
 * 类名称:PsiCheckDetailController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.service.PsiStockService;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 盘点明细Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@RestController
@RequestMapping("psi/checkdetail")
public class PsiCheckDetailController extends AbstractController {
    @Autowired
    private PsiCheckDetailService psiCheckDetailService;
    @Autowired
    private PsiStockService stockService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
//    @RequestMapping("/listAll")
//    @RequiresPermissions("psi:check:list")
//    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
//        List<PsiCheckDetailEntity> list = psiCheckDetailService.listAll(params);
//        return RestResponse.success("list", list);
//    }

    /**
     * 分页查询盘点明细
     *
     * @param params 查询参数
     * @return RestResponse
     */
//    @GetMapping("/list")
//    @RequiresPermissions("psi:check:list")
//    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
//        PageInfo page = psiCheckDetailService.listPage(current,size,params);
//        return RestResponse.success("page", page);
//    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
//    @RequestMapping("/info/{id}")
//    @RequiresPermissions("psi:check:info")
//    public RestResponse info(@PathVariable("id") String id) {
//        PsiCheckDetailEntity psiCheckDetail = psiCheckDetailService.getById(id);
//        return RestResponse.success("info", psiCheckDetail);
//    }

    /**
     * 新增盘点明细
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("新增盘点明细")
//    @RequestMapping("/save")
//    @RequiresPermissions("psi:check:save")
//    public RestResponse save(@RequestBody PsiCheckDetailEntity entity) {
//        psiCheckDetailService.addEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 修改盘点明细
     *
     * @param entity
     * @return RestResponse
     */
//    @SysLog("修改盘点明细")
//    @RequestMapping("/update")
//    @RequiresPermissions("psi:check:update")
//    public RestResponse update(@RequestBody PsiCheckDetailEntity entity) {
//        psiCheckDetailService.updateEntity(entity);
//        return RestResponse.success();
//    }

    /**
     * 根据主键删除盘点明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除盘点明细")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:checkdetail:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        Arrays.stream(ids).forEach(id->{
            PsiCheckDetailEntity item = psiCheckDetailService.getById(id);
            stockService.deleteBySkuId(item.getCid(),item.getGoodsId(),item.getSkuId());
            psiCheckDetailService.deleteEntity(item.getId());
        });
        return RestResponse.success();
    }
}
