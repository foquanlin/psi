package com.tongyi.modules.gen.controller;

import com.tongyi.common.annotation.SysLog;
import com.tongyi.common.utils.DateUtils;
import com.tongyi.common.utils.RestResponse;
import com.tongyi.common.xss.XssHttpServletRequestWrapper;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.gen.service.SysGeneratorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author 林佛权
 * @email 147657060@qq.com
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
    @GetMapping("/list")
    @RequiresPermissions("sys:generator:list")
    public RestResponse list(@RequestParam(value = "page",defaultValue = "1") int current,@RequestParam(value = "limit",defaultValue = "10")int size,@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo page = sysGeneratorService.listPage(current,size,params);
        return RestResponse.success().put("page", page);
    }

    /**
     * 生成代码
     *
     * @param request  request
     * @param response response
     */
    @SysLog("生成代码")
    @PostMapping("/code")
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
        response.getOutputStream().flush();
        IOUtils.closeQuietly(response.getOutputStream());
    }
}
