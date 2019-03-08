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
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
@Slf4j
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * UEditor后台配置请求
     */
    private static final String CONFIG_ACTION = "config";

    /**
     * 文件管理列表
     */
    private static final String LIST_ACTION = "listimage";

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
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:upload")
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file,
                         @RequestParam(value = "action", required = false) String action,
                         @RequestParam(value = "page", required = false) String page,
                         @RequestParam(value = "limit", required = false) String limit) throws Exception {
        if (null != action) {
            switch (action) {
                // 分页管理图片列表
                case LIST_ACTION:
                    Map<String, Object> params = new HashMap<>(4);
                    params.put("page", page);
                    params.put("limit", limit);
                    return RestResponse.success().put("state", "SUCCESS").put("list", sysOssService.queryPage(params));
                //获取UEditor后台配置
                case CONFIG_ACTION:
                    return getConfig();
                default:
                    // 其他的不处理
                    log.debug(action);
            }
        }
        if (null == file || file.isEmpty()) {
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

        //返回兼容UEditor的参数
        return RestResponse.success().put("url", url).put("state", "SUCCESS").put("title", url).put("original", url);
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

    /**
     * Ueditor后台配置，前后端通信相关的配置,注释只允许使用多行方式
     *
     * @return
     */
    private String getConfig() {
        return "{\n" +
                "    /* 上传图片配置项 */\n" +
                "    \"imageActionName\": \"uploadimage\", /* 执行上传图片的action名称 */\n" +
                "    \"imageFieldName\": \"file\", /* 提交的图片表单名称 */\n" +
                "    \"imageMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 上传图片格式显示 */\n" +
                "    \"imageCompressEnable\": true, /* 是否压缩图片,默认是true */\n" +
                "    \"imageCompressBorder\": 1600, /* 图片压缩最长边限制 */\n" +
                "    \"imageInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    \"imageUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"localSavePathPrefix\":\"upload/images/inform\",\n" +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    /* 涂鸦图片上传配置项 */\n" +
                "    \"scrawlActionName\": \"uploadscrawl\", /* 执行上传涂鸦的action名称 */\n" +
                "    \"scrawlFieldName\": \"file\", /* 提交的图片表单名称 */\n" +
                "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"scrawlMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"scrawlUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"scrawlInsertAlign\": \"none\",\n" +
                "    /* 截图工具上传 */\n" +
                "    \"snapscreenActionName\": \"uploadimage\", /* 执行上传截图的action名称 */\n" +
                "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"snapscreenUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"snapscreenInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    /* 抓取远程图片配置 */\n" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
                "    \"catcherActionName\": \"catchimage\", /* 执行抓取远程图片的action名称 */\n" +
                "    \"catcherFieldName\": \"file\", /* 提交的图片列表表单名称 */\n" +
                "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"catcherUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"catcherMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 抓取图片格式显示 */\n" +
                "    /* 上传视频配置 */\n" +
                "    \"videoActionName\": \"uploadvideo\", /* 执行上传视频的action名称 */\n" +
                "    \"videoFieldName\": \"file\", /* 提交的视频表单名称 */\n" +
                "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"videoUrlPrefix\": \"\", /* 视频访问路径前缀 */\n" +
                "    \"videoMaxSize\": 102400000, /* 上传大小限制，单位B，默认100MB */\n" +
                "    \"videoAllowFiles\": [\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], /* 上传视频格式显示 */\n" +
                "    /* 上传文件配置 */\n" +
                "    \"fileActionName\": \"uploadfile\", /* controller里,执行上传视频的action名称 */\n" +
                "    \"fileFieldName\": \"file\", /* 提交的文件表单名称 */\n" +
                "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", /* 上传保存路径,可以自定义保存路径和文件名格式 */\n" +
                "    \"fileUrlPrefix\": \"\", /* 文件访问路径前缀 */\n" +
                "    \"fileMaxSize\": 51200000, /* 上传大小限制，单位B，默认50MB */\n" +
                "    \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ], /* 上传文件格式显示 */\n" +
                "    /* 列出指定目录下的图片 */\n" +
                "    \"imageManagerActionName\": \"listimage\", /* 执行图片管理的action名称 */\n" +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\", /* 指定要列出图片的目录 */\n" +
                "    \"imageManagerListSize\": 15, /* 每次列出文件数量 */\n" +
                "    \"imageManagerUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"imageManagerInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 列出的文件类型 */\n" +
                "    /* 列出指定目录下的文件 */\n" +
                "    \"fileManagerActionName\": \"listimage\", /* 执行文件管理的action名称 */\n" +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\", /* 指定要列出文件的目录 */\n" +
                "    \"fileManagerUrlPrefix\": \"\", /* 文件访问路径前缀 */\n" +
                "    \"fileManagerListSize\": 15, /* 每次列出文件数量 */\n" +
                "    \"fileManagerAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ] /* 列出的文件类型 */\n" +
                "}";
    }
}