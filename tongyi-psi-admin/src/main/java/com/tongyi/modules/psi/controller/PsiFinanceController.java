/*
 * 项目名称:进销存
 * 类名称:PsiFinanceController.java
 * 包名称:com.tongyi.modules.psi.controller
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.controller;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.psi.service.PsiFinanceDetailService;
import com.tongyi.modules.psi.service.execute.FinanceCreateExecute;
import com.tongyi.modules.sys.controller.AbstractController;
import com.tongyi.modules.psi.entity.PsiFinanceEntity;
import com.tongyi.modules.psi.service.PsiFinanceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tongyi.core.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 非销售Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@RestController
@RequestMapping("psi/finance")
public class PsiFinanceController extends AbstractController {
    @Autowired
    private PsiFinanceService psiFinanceService;
    @Autowired
    private PsiFinanceDetailService financeDetailService;

    @Autowired
    private FinanceCreateExecute financeCreateExecute;
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping(value="/listAll")
    @RequiresPermissions(value = {"psi:nosalein:list","psi:nobuyout:list"},logical = Logical.OR)
    public RestResponse listAll(@RequestParam Map<String, Object> params) {
        List<PsiFinanceEntity> list = psiFinanceService.listAll(params);
        return RestResponse.success("list", list);
    }

    /**
     * 分页查询非销售
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions(value = {"psi:nosalein:list","psi:nobuyout:list"},logical = Logical.OR)
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        PageInfo page = psiFinanceService.listPage(current,size,params);
        return RestResponse.success("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions(value = {"psi:nosalein:info","psi:nobuyout:info"},logical = Logical.OR)
    public RestResponse info(@PathVariable("id") String id) {
        PsiFinanceEntity psiFinance = psiFinanceService.getById(id);
        Map<String,Object> params = new HashMap<>();
        params.put("fid",psiFinance.getId());
        financeDetailService.listAll(params);
        return RestResponse.success("info", psiFinance);
    }

    /**
     * 新增非销售
     *
     * @param json
     * @return RestResponse
     */
    @SysLog("新增非销售")
    @PostMapping("/save")
    @RequiresPermissions(value = {"psi:nosalein:save","psi:nobuyout:save"},logical = Logical.OR)
    public RestResponse save(@RequestBody String json) {
        String createUid = getUserId();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        PsiFinanceEntity entity = PsiFinanceEntity.newEntity(createUid);
        financeCreateExecute.apply(entity,jsonObject);
        return RestResponse.success();
    }

    /**
     * 修改非销售
     *
     * @param entity
     * @return RestResponse
     */
    @SysLog("修改非销售")
    @PostMapping("/update")
    @RequiresPermissions(value = {"psi:nosalein:update","psi:nobuyout:update"},logical = Logical.OR)
    public RestResponse update(@RequestBody PsiFinanceEntity entity) {
        psiFinanceService.updateEntity(entity);
        return RestResponse.success();
    }

    /**
     * 根据主键删除非销售
     *
     * @param ids
     * @return RestResponse
     */
    @SysLog("删除非销售")
    @PostMapping("/delete")
    @RequiresPermissions(value = {"psi:nosalein:delete","psi:nobuyout:delete"},logical = Logical.OR)
    public RestResponse delete(@RequestBody String[] ids) {
        psiFinanceService.deleteBatch(ids);
        return RestResponse.success();
    }
}
