/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiAllocationEntity;
import com.tongyi.modules.psi.service.PsiAllocationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 调拨单Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RestController
@RequestMapping("psi/allocation")
public class PsiAllocationController extends AbstractController {
    @Autowired
    private PsiAllocationService psiAllocationService;
    @Autowired
    private PsiAllocationGoodsService psiAllocationGoodsService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("psi:allocation:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiAllocationEntity> list = psiAllocationService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询调拨单
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:allocation:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiAllocationService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("psi:allocation:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiAllocationEntity psiAllocation = psiAllocationService.getById(id);
        return RestResponse.success("info", psiAllocation);
    }

    /**
     * 新增调拨单
     *
     * @return RestResponse
     */
    @SysLog("新增调拨单")
    @RequestMapping("/save")
    @RequiresPermissions("psi:allocation:save")
    public RestResponse save(@RequestBody Map<String,Object> map) {
        String inWarehouseId = (String)map.get("inWarehouseId");
        String outWarehouseId = (String)map.get("outWarehouseId");
        String memo = (String)map.get("memo");
        List<PsiAllocationGoodsEntity> list = (List)map.get("dataList");

        PsiAllocationEntity entity = PsiAllocationEntity.newEntity(inWarehouseId,outWarehouseId,memo);
        Objects.requireNonNull(entity);
        psiAllocationService.addEntity(entity);
        Objects.requireNonNull(list);
        list.forEach(item->{
            item.setAllocationId(entity.getId());
            psiAllocationGoodsService.addEntity(item);
        });
        return RestResponse.success();
    }

    /**
     * 修改调拨单
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改调拨单")
    @RequestMapping("/update")
    @RequiresPermissions("psi:allocation:update")
    public RestResponse update(@RequestBody PsiAllocationEntity entity) {
        psiAllocationService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除调拨单
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除调拨单")
    @RequestMapping("/delete")
    @RequiresPermissions("psi:allocation:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiAllocationService.deleteBatch(ids);
        return RestResponse.success();
    }
}
