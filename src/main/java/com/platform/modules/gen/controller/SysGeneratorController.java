package com.platform.modules.gen.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.DateUtils;
import com.platform.common.utils.RestResponse;
import com.platform.common.xss.XssHttpServletRequestWrapper;
import com.platform.modules.gen.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年1月3日 下午6:35:28
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 分页查询所有表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:generator:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Page page = sysGeneratorService.queryPage(params);
        return RestResponse.success().put("page", page);
    }

    /**
     * 生成代码
     *
     * @param request  request
     * @param response response
     */
    @SysLog("生成代码")
    @RequestMapping("/code")
    @RequiresPermissions("sys:generator:code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表名，不进行xss过滤
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String tables = orgRequest.getParameter("tables");
        String projectName = orgRequest.getParameter("projectName");
        String packageName = orgRequest.getParameter("packageName");
        String author = orgRequest.getParameter("author");
        String[] tableNames = tables.split(",");

        byte[] data = sysGeneratorService.generatorCode(tableNames, projectName, packageName, author);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"AutoCode" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
