/*
 * 项目名称:项目名称
 * 类名称:PsiCostTypeController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 00:14:03
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiCostTypeEntity;
import com.tongyi.modules.psi.service.PsiCostTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 收支类型Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 00:14:03
 */
@RestController
@RequestMapping("psi/costtype")
public class PsiCostTypeController extends AbstractController {
    @Autowired
    private PsiCostTypeService psiCostTypeService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:costtype:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiCostTypeEntity> list = psiCostTypeService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询收支类型
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:costtype:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiCostTypeService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:costtype:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiCostTypeEntity psiCostType = psiCostTypeService.getById(id);
        return RestResponse.success("info", psiCostType);
    }

    /**
     * 新增收支类型
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增收支类型")
    @RequestMapping("/save")
    @RequiresPermissions("psi:costtype:save")
    public RestResponse save(@RequestBody PsiCostTypeEntity entity) {
        psiCostTypeService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改收支类型
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改收支类型")
    @RequestMapping("/update")
    @RequiresPermissions("psi:costtype:update")
    public RestResponse update(@RequestBody PsiCostTypeEntity entity) {
        psiCostTypeService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除收支类型
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除收支类型")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:costtype:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiCostTypeService.deleteBatch(ids);
        return RestResponse.success();
    }
}
