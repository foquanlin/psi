/*
 * 项目名称:platform-plus
 * 类名称:SysOrgController.java
 * 包名称:com.platform.modules.sys.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-21 11:29:22        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.controller;

import com.platform.common.annotation.SysLog;
import com.platform.common.utils.RestResponse;
import com.platform.modules.sys.entity.SysOrgEntity;
import com.platform.modules.sys.entity.SysUserEntity;
import com.platform.modules.sys.service.SysOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织机构Controller
 *
 * @author 李鹏军
 * @date 2019-01-21 11:29:22
 */
@RestController
@RequestMapping("sys/org")
public class SysOrgController extends AbstractController {
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @RequestMapping("/queryAll")
    @RequiresPermissions("sys:org:list")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<SysOrgEntity> list = sysOrgService.queryAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 根据主键查询详情
     *
     * @param orgNo 主键
     * @return RestResponse
     */
    @RequestMapping("/info/{orgNo}")
    @RequiresPermissions("sys:org:info")
    public RestResponse info(@PathVariable("orgNo") String orgNo) {
        SysOrgEntity sysOrg = sysOrgService.getById(orgNo);

        return RestResponse.success().put("org", sysOrg);
    }

    /**
     * 保存
     *
     * @param sysOrg sysOrg
     * @return RestResponse
     */
    @SysLog("保存机构")
    @RequestMapping("/save")
    @RequiresPermissions("sys:org:save")
    public RestResponse save(@RequestBody SysOrgEntity sysOrg) {
        SysUserEntity user = getUser();
        sysOrg.setCreateUserId(user.getUserId());
        sysOrgService.add(sysOrg);
        return RestResponse.success();
    }

    /**
     * 修改
     *
     * @param sysOrg sysOrg
     * @return RestResponse
     */
    @SysLog("修改机构")
    @RequestMapping("/update")
    @RequiresPermissions("sys:org:update")
    public RestResponse update(@RequestBody SysOrgEntity sysOrg) {
        sysOrgService.update(sysOrg);
        return RestResponse.success();
    }

    /**
     * 删除
     *
     * @param orgNo 机构编码
     * @return RestResponse
     */
    @SysLog("删除机构")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:org:delete")
    public RestResponse delete(@RequestBody String orgNo) {
        orgNo = orgNo.replaceAll("\"", "");
        List<SysOrgEntity> sysOrgEntities = sysOrgService.queryListByOrgNo(orgNo);
        if (sysOrgEntities.size() > 0) {
            return RestResponse.error("请先删除子机构");
        } else {
            sysOrgService.delete(orgNo);
        }
        return RestResponse.success();
    }
}
