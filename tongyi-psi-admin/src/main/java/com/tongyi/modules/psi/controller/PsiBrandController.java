/*
 * 项目名称:项目名称
 * 类名称:PsiBrandController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiBrandEntity;
import com.tongyi.modules.psi.service.PsiBrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 品牌Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/brand")
public class PsiBrandController extends AbstractController {
    @Autowired
    private PsiBrandService psiBrandService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:brand:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiBrandEntity> list = psiBrandService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询品牌
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:brand:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiBrandService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:brand:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiBrandEntity psiBrand = psiBrandService.getById(id);
        return RestResponse.success("info", psiBrand);
    }

    /**
     * 新增品牌
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增品牌")
    @RequestMapping("/save")
    @RequiresPermissions("psi:brand:save")
    public RestResponse save(@RequestBody PsiBrandEntity entity) {
        psiBrandService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改品牌
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改品牌")
    @RequestMapping("/update")
    @RequiresPermissions("psi:brand:update")
    public RestResponse update(@RequestBody PsiBrandEntity entity) {
        psiBrandService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除品牌
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除品牌")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:brand:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiBrandService.deleteBatch(ids);
        return RestResponse.success();
    }
}
