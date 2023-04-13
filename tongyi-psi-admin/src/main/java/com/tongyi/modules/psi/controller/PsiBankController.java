/*
 * 项目名称:项目名称
 * 类名称:PsiBankController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiBankEntity;
import com.tongyi.modules.psi.service.PsiBankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 银行账户Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/bank")
public class PsiBankController extends AbstractController {
    @Autowired
    private PsiBankService psiBankService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/listAll")
    @RequiresPermissions("psi:bank:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<PsiBankEntity> list = psiBankService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询银行账户
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:bank:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiBankService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:bank:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiBankEntity psiBank = psiBankService.getById(id);
        return RestResponse.success("info", psiBank);
    }

    /**
     * 新增银行账户
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增银行账户")
    @PostMapping("/save")
    @RequiresPermissions("psi:bank:save")
    public RestResponse save(@RequestBody PsiBankEntity entity) {
        psiBankService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改银行账户
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改银行账户")
    @PostMapping("/update")
    @RequiresPermissions("psi:bank:update")
    public RestResponse update(@RequestBody PsiBankEntity entity) {
        psiBankService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除银行账户
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除银行账户")
    @PostMapping("/delete")
    @RequiresPermissions("psi:bank:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiBankService.deleteBatch(ids);
        return RestResponse.success();
    }

    @SysLog("设置默认银行账户")
    @GetMapping("/default")
    @RequiresPermissions("psi:bank:default")
    public RestResponse defaultBank(@RequestParam("id") String id) {
        psiBankService.defaultBank(id);
        return RestResponse.success();
    }
}
