/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationGoodsController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 调拨单明细Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@RestController
@RequestMapping("psi/allocationgoods")
public class PsiAllocationGoodsController extends AbstractController {
    @Autowired
    private PsiAllocationGoodsService psiAllocationGoodsService;

    /**
     * 根据主键删除调拨单明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除调拨单明细")
    @PostMapping("/delete")
    @RequiresPermissions("psi:allocationgoods:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiAllocationGoodsService.deleteEntity(ids);
        return RestResponse.success();
    }
}
