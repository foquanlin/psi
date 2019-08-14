/*
 * 项目名称:platform-plus
 * 类名称:QcloudCloudStorageService.java
 * 包名称:com.platform.modules.oss.cloud
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.oss.cloud;

import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 腾讯云存储
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
public class QcloudCloudStorageService extends AbstractCloudStorageService {
    private COSClient client;

    public QcloudCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }

    private void init() {
        COSCredentials cred = new BasicCOSCredentials(config.getQcloudSecretId(), config.getQcloudSecretKey());

        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(config.getQcloudRegion());
        ClientConfig clientConfig = new ClientConfig(region);

        // 3 生成 cos 客户端。
        client = new COSClient(cred, clientConfig);
    }

    @Override
    public String upload(byte[] data, String path) {
        return this.upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        //腾讯云必需要以"/"开头
        if (!path.startsWith(Constant.SLASH)) {
            path = "/" + path;
        }

        try {
            //上传到腾讯云
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getQcloudBucketName(), path, inputStream, new ObjectMetadata());
            client.putObject(putObjectRequest);
            return config.getQcloudDomain() + path;
        } catch (Exception e) {
            throw new BusinessException("上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQcloudPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix));
    }
}
