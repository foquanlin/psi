/*
 * 项目名称:进销存
 * 类名称:PsiFinanceDetailController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiFinanceDetailEntity;
import com.tongyi.modules.psi.service.PsiFinanceDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 非销售明细Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@RestController
@RequestMapping("psi/financedetail")
public class PsiFinanceDetailController extends AbstractController {
    @Autowired
    private PsiFinanceDetailService psiFinanceDetailService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping(value="/listAll")
    @RequiresPermissions("psi:financedetail:list")
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiFinanceDetailEntity> list = psiFinanceDetailService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询非销售明细
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("psi:financedetail:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiFinanceDetailService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("psi:financedetail:info")
    public RestResponse info(@PathVariable("id") String id) {
        PsiFinanceDetailEntity psiFinanceDetail = psiFinanceDetailService.getById(id);
        return RestResponse.success("info", psiFinanceDetail);
    }

    /**
     * 新增非销售明细
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("新增非销售明细")
    @PostMapping("/save")
    @RequiresPermissions("psi:financedetail:save")
    public RestResponse save(@RequestBody PsiFinanceDetailEntity entity) {
        psiFinanceDetailService.addEntity(entity);
        return RestResponse.success();
    }

    /**
     * 修改非销售明细
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改非销售明细")
    @PostMapping("/update")
    @RequiresPermissions("psi:financedetail:update")
    public RestResponse update(@RequestBody PsiFinanceDetailEntity entity) {
        psiFinanceDetailService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除非销售明细
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除非销售明细")
    @PostMapping("/delete")
    @RequiresPermissions("psi:financedetail:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        psiFinanceDetailService.deleteBatch(ids);
        return RestResponse.success();
    }
}
