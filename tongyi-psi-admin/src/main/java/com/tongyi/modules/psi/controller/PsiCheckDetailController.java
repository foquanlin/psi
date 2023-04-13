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
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据主键删除盘点明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除盘点明细")
    @PostMapping("/delete")
    @RequiresPermissions("psi:checkdetail:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiCheckDetailService.deleteEntity(ids);
        return RestResponse.success();
    }
}
