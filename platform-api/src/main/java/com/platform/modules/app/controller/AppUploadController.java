package com.platform.modules.app.controller;

import com.platform.common.exception.BusinessException;
import com.platform.common.utils.RestResponse;
import com.platform.modules.oss.cloud.UploadFactory;
import com.platform.modules.oss.entity.SysOssEntity;
import com.platform.modules.oss.service.SysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者: @author Lipengjun <br>
 * 时间: 2017-09-08 13:20<br>
 * 描述: ApiUploadController <br>
 */
@Api(tags = "ApiUploadController|上传管理控制器")
@RestController
@RequestMapping("/app/upload")
public class AppUploadController {

    @Autowired
    private SysOssService sysOssService;

    /**
     * 上传文件
     *
     * @param file file
     * @return RestResponse
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件，form表单提交")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "file", value = "文件流对象", required = true, dataType = "__file")
    })
    public RestResponse upload(@RequestParam("file") MultipartFile file) {
        if (null == file || file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String url;
        try {
            url = UploadFactory.build().uploadSuffix(file.getBytes(), suffix);
        } catch (IOException e) {
            return RestResponse.error("上传文件失败");
        }

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return RestResponse.success().put("url", url);
    }

    /**
     * 上传多个文件
     */
    @PostMapping("/uploadFiles")
    @ApiOperation(value = "上传多个文件", notes = "上传多个文件，form表单提交")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "files", value = "文件流对象,接收数组格式", required = true, dataType = "__file", allowMultiple = true)
    })
    public RestResponse uploadFiles(@RequestParam("files") MultipartFile[] files) {
        if (null == files || files.length == 0) {
            throw new BusinessException("上传文件不能为空");
        }
        List<String> urls = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                //上传文件
                urls.add(UploadFactory.build().uploadSuffix(file.getBytes(), suffix));

            }
        } catch (IOException e) {
            return RestResponse.error("上传文件失败");
        }
        return RestResponse.success().put("urls", urls);
    }
}
