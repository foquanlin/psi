/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiGoodsEntity;
import com.tongyi.modules.psi.service.PsiGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 商品Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RestController
@RequestMapping("psi/goods")
public class PsiGoodsController extends AbstractController {
    @Autowired
    private PsiGoodsService psiGoodsService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @RequiresPermissions("psi:goods:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiGoodsEntity> list = psiGoodsService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询商品
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:goods:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiGoodsService.listPage(current,size,params);
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
        PsiGoodsEntity psiGoods = psiGoodsService.getById(id);
        return RestResponse.success("info", psiGoods);
    }

    /**
     * 新增商品
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增商品")
    @PostMapping("/save")
    @RequiresPermissions("psi:goods:save")
    public RestResponse save(@RequestBody PsiGoodsEntity entity) {
        psiGoodsService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改商品
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改商品")
    @PostMapping("/update")
    @RequiresPermissions("psi:goods:update")
    public RestResponse update(@RequestBody PsiGoodsEntity entity) {
        psiGoodsService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除商品
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除商品")
    @PostMapping("/delete")
    @RequiresPermissions("psi:goods:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiGoodsService.deleteBatch(ids);
        return RestResponse.success();
    }
}
