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
import org.apache.commons.io.IOUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 服务器存储
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
public class DiskCloudStorageService extends AbstractCloudStorageService {

    public DiskCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }

    private void init() {

    }

    @Override
    public String upload(byte[] data, String path) {
        if (data.length < Constant.THREE || "".equals(path)) {
            throw new BusinessException("上传文件为空");
        }
        //本地存储必需要以"/"开头
        if (!path.startsWith(Constant.SLASH)) {
            path = "/" + path;
        }
        try {
            String fileName = config.getDiskPath() + path;

            String dateDir = path.split("/")[1];

            //文件夹
            File dirFile = new File(config.getDiskPath() + "/" + dateDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //打开输入流
            FileImageOutputStream imageOutput = new FileImageOutputStream(file);
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("上传文件失败", e);
        }

        return config.getProxyServer() + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
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
