/*
 * 项目名称:项目名称
 * 类名称:PsiOrderExpressController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiOrderExpressEntity;
import com.tongyi.modules.psi.service.PsiOrderExpressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 运杂费Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RestController
@RequestMapping("psi/orderexpress")
public class PsiOrderExpressController extends AbstractController {
    @Autowired
    private PsiOrderExpressService psiOrderExpressService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:orderexpress:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiOrderExpressEntity> list = psiOrderExpressService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询运杂费
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:orderexpress:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiOrderExpressService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:orderexpress:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiOrderExpressEntity psiOrderExpress = psiOrderExpressService.getById(id);
        return RestResponse.success("info", psiOrderExpress);
    }

    /**
     * 新增运杂费
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增运杂费")
    @PostMapping("/save")
    @RequiresPermissions("psi:orderexpress:save")
    public RestResponse save(@RequestBody PsiOrderExpressEntity entity) {
        psiOrderExpressService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改运杂费
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改运杂费")
    @PostMapping("/update")
    @RequiresPermissions("psi:orderexpress:update")
    public RestResponse update(@RequestBody PsiOrderExpressEntity entity) {
        psiOrderExpressService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除运杂费
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除运杂费")
    @PostMapping("/delete")
    @RequiresPermissions("psi:orderexpress:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiOrderExpressService.deleteBatch(ids);
        return RestResponse.success();
    }
}
