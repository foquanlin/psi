/*
 * 项目名称:项目名称
 * 类名称:PsiUnitController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiUnitEntity;
import com.tongyi.modules.psi.service.PsiUnitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 单位Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/unit")
public class PsiUnitController extends AbstractController {
    @Autowired
    private PsiUnitService psiUnitService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:unit:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiUnitEntity> list = psiUnitService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询单位
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:unit:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiUnitService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:unit:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiUnitEntity psiUnit = psiUnitService.getById(id);
        return RestResponse.success("info", psiUnit);
    }

    /**
     * 新增单位
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增单位")
    @PostMapping("/save")
    @RequiresPermissions("psi:unit:save")
    public RestResponse save(@RequestBody PsiUnitEntity entity) {
        psiUnitService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改单位
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改单位")
    @PostMapping("/update")
    @RequiresPermissions("psi:unit:update")
    public RestResponse update(@RequestBody PsiUnitEntity entity) {
        psiUnitService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除单位
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除单位")
    @PostMapping("/delete")
    @RequiresPermissions("psi:unit:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiUnitService.deleteBatch(ids);
        return RestResponse.success();
    }
}
