/*
 * 项目名称:platform-plus
 * 类名称:SysOssController.java
 * 包名称:com.platform.modules.oss.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.oss.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.platform.common.annotation.SysLog;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.platform.common.utils.RestResponse;
import com.platform.common.validator.ValidatorUtils;
import com.platform.common.validator.group.AliyunGroup;
import com.platform.common.validator.group.DiskGroup;
import com.platform.common.validator.group.QcloudGroup;
import com.platform.common.validator.group.QiniuGroup;
import com.platform.modules.oss.cloud.CloudStorageConfig;
import com.platform.modules.oss.cloud.UploadFactory;
import com.platform.modules.oss.entity.SysOssEntity;
import com.platform.modules.oss.service.SysOssService;
import com.platform.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:oss:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        Page page = sysOssService.queryPage(params);

        return RestResponse.success().put("page", page);
    }

    /**
     * 云存储配置信息
     *
     * @return RestResponse
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:config")
    public RestResponse config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        return RestResponse.success().put("config", config);
    }

    /**
     * 修改云存储配置信息
     *
     * @param config config
     * @return RestResponse
     */
    @SysLog("修改云存储配置信息")
    @PostMapping("/saveConfig")
    @RequiresPermissions("sys:oss:config")
    public RestResponse saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        } else if (config.getType() == Constant.CloudService.DISCK.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, DiskGroup.class);
        }

        sysConfigService.updateValueByKey(Constant.CLOUD_STORAGE_CONFIG_KEY, new Gson().toJson(config));

        return RestResponse.success();
    }

    /**
     * 上传文件
     *
     * @param file file
     * @return RestResponse
     */
    @PostMapping("/upload")
    @RequiresPermissions("sys:oss:upload")
    public RestResponse upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = UploadFactory.build().uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return RestResponse.success().put("url", url);
    }

    /**
     * 删除文件上传记录
     *
     * @param ids ids
     * @return RestResponse
     */
    @SysLog("删除文件上传记录")
    @PostMapping("/delete")
    @RequiresPermissions("sys:oss:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysOssService.removeByIds(Arrays.asList(ids));

        return RestResponse.success();
    }

}