/*
 * 项目名称:platform-plus
 * 类名称:CloudStorageService.java
 * 包名称:com.platform.modules.oss.cloud
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.oss.cloud;

import com.platform.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
public abstract class AbstractCloudStorageService {
    /**
     * 云存储配置信息
     */
    CloudStorageConfig config;

    /**
     * 文件路径
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + "/" + path;
        }

        return path + suffix;
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data   文件字节数组
     * @param suffix 后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param suffix      后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);

}
