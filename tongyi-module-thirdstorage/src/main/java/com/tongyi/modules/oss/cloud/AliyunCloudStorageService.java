/*
 * 项目名称:tongyi-component
 * 类名称:AliyunCloudStorageService.java
 * 包名称:com.tongyi.modules.oss.cloud
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.oss.cloud;

import com.aliyun.oss.OSSClient;
import com.tongyi.common.exception.BusinessException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 *
 * @author 林佛权
 */
public class AliyunCloudStorageService extends AbstractCloudStorageService {
    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
        } catch (Exception e) {
            throw new BusinessException("上传文件失败，请检查配置信息", e);
        }

        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix));
    }
}
