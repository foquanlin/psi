/*
 * 项目名称:tongyi-component
 * 类名称:ActReModelController.java
 * 包名称:com.tongyi.modules.act.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.act.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.modules.act.entity.ActReModelEntity;
import com.tongyi.modules.act.service.ActReModelService;
import jakarta.servlet.http.HttpServletResponse;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Controller
 * @author 林佛权
 */
@RestController
@RequestMapping("act/remodel")
public class ActReModelController {
    @Autowired
    private ActReModelService actReModelService;
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("act:remodel:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        Page page = actReModelService.queryPage(params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 新增
     *
     * @param actReModel actReModel
     * @return RestResponse
     */
    @SysLog("新增")
    @PostMapping("/save")
    @RequiresPermissions("act:remodel:save")
    public RestResponse save(@RequestBody ActReModelEntity actReModel) {
        String modelId = "";
        try {
            Model model = actReModelService.add(actReModel);
            modelId = model.getId();
        } catch (Exception e) {
            RestResponse.error(e.getMessage());
        }
        return RestResponse.success().put("modelId", modelId);
    }

    /**
     * 根据Model部署流程
     *
     * @param id 标识
     * @return RestResponse
     */
    @SysLog("部署流程文件")
    @RequestMapping("/deploy")
    @RequiresPermissions("act:remodel:deploy")
    public RestResponse deploy(String id) {
        String msg = actReModelService.deploy(id);
        return RestResponse.success().put("msg", msg);
    }

    /**
     * 导出model的xml文件
     *
     * @param id       model标识
     * @param response 响应
     */
    @RequestMapping(value = "export")
    public void export(String id, HttpServletResponse response) {
        try {
            response.setContentType("text/xml; charset=utf-8");
            BpmnModel bpmnModel = actReModelService.getBpmModel(id);
            String filename = URLEncoder.encode(bpmnModel.getMainProcess().getName() + ".bpmn20.xml", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName="+filename);

            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new BusinessException("导出model的xml文件失败，模型ID=" + id, e);
        }
    }

    /**
     * 根据主键删除
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除")
    @PostMapping("/delete")
    @RequiresPermissions("act:remodel:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        actReModelService.deleteBatch(ids);

        return RestResponse.success();
    }
}
