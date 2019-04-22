/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefController.java
 * 包名称:com.platform.modules.act.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.annotation.SysLog;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.RestResponse;
import com.platform.modules.act.service.ActReProcdefService;
import com.platform.modules.sys.controller.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * Controller
 *
 * @author 李鹏军
 * @date 2019-03-18 09:47:54
 */
@RestController
@RequestMapping("act/reprocdef")
public class ActReProcdefController extends AbstractController {
    @Autowired
    private ActReProcdefService actReProcdefService;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("act:reprocdef:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        Page page = actReProcdefService.queryPage(params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 读取资源，通过部署ID
     *
     * @param id       流程定义ID
     * @param proInsId 流程实例ID
     * @param resType  资源类型(xml|image)
     * @param response 响应
     * @throws Exception 读写流异常
     */
    @RequestMapping("/read")
    public void resourceRead(String id, String proInsId, String resType,
                             HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream = actReProcdefService.resourceRead(id, proInsId, resType);
        byte[] b = new byte[1024];
        int len = -1;
        int lenEnd = 1024;
        while ((len = resourceAsStream.read(b, 0, lenEnd)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 部署流程文件
     *
     * @param file file
     * @return RestResponse
     */
    @SysLog("部署流程文件")
    @RequestMapping("/deploy")
    @RequiresPermissions("act:reprocdef:deploy")
    public RestResponse deploy(MultipartFile file) {
        String exportDir = this.getClass().getResource("/").getPath();
        String fileName = file.getOriginalFilename();
        String msg = "";
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException("请选择要部署的流程文件");
        } else {
            try {
                msg = actReProcdefService.deploy(exportDir, file);
            } catch (Exception e) {
                return RestResponse.error(e.getMessage());
            }
        }
        return RestResponse.success().put("msg", msg);
    }

    /**
     * 转为模型
     *
     * @param id id
     * @return RestResponse
     */
    @SysLog("转为模型")
    @RequestMapping("/convertToModel")
    @RequiresPermissions("act:reprocdef:convertToModel")
    public RestResponse convertToModel(String id) {
        try {
            actReProcdefService.convertToModel(id);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
        return RestResponse.success();
    }

    /**
     * 启动流程实例，通过processDefinitionId
     *
     * @param processDefinitionId processDefinitionId
     * @return RestResponse
     */
    @SysLog("启动流程实例")
    @RequestMapping("/startProcessInstanceById")
    @RequiresPermissions("act:reprocdef:startProcessInstanceById")
    public RestResponse startProcessInstanceById(String processDefinitionId) {
        try {
            actReProcdefService.startProcessInstanceById(processDefinitionId);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
        return RestResponse.success();
    }

    /**
     * 激活 / 挂起
     *
     * @param state
     * @param id
     * @return
     */
    @SysLog("激活 / 挂起")
    @RequestMapping("/update")
    @RequiresPermissions("act:reprocdef:update")
    public RestResponse update(int state, String id) {

        String msg = actReProcdefService.updateState(state, id);

        return RestResponse.success().put("msg", msg);
    }

    /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署标识
     * @return RestResponse
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("act:reprocdef:delete")
    public RestResponse delete(@RequestBody String[] deploymentId) {
        actReProcdefService.deleteBatch(deploymentId);

        return RestResponse.success();
    }
}
