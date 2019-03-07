/*
 * 项目名称:platform-plus
 * 类名称:CloudStorageConfig.java
 * 包名称:com.platform.modules.oss.cloud
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/17 16:21    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.oss.cloud;


import com.platform.common.validator.group.AliyunGroup;
import com.platform.common.validator.group.DiskGroup;
import com.platform.common.validator.group.QcloudGroup;
import com.platform.common.validator.group.QiniuGroup;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 云存储配置信息
 *
 * @author 李鹏军
 * @date 2019-01-17 16:21:01
 */
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型 1：七牛  2：阿里云  3：腾讯云  4：服务器存储
     */
    @Range(min = 1, max = 4, message = "类型错误")
    private Integer type;

    /**
     * 七牛绑定的域名
     */
    @NotBlank(message = "七牛绑定的域名不能为空", groups = QiniuGroup.class)
    @URL(message = "七牛绑定的域名格式不正确", groups = QiniuGroup.class)
    private String qiniuDomain;
    /**
     * 七牛路径前缀
     */
    private String qiniuPrefix;
    /**
     * 七牛ACCESS_KEY
     */
    @NotBlank(message = "七牛AccessKey不能为空", groups = QiniuGroup.class)
    private String qiniuAccessKey;
    /**
     * 七牛SECRET_KEY
     */
    @NotBlank(message = "七牛SecretKey不能为空", groups = QiniuGroup.class)
    private String qiniuSecretKey;
    /**
     * 七牛存储空间名
     */
    @NotBlank(message = "七牛空间名不能为空", groups = QiniuGroup.class)
    private String qiniuBucketName;

    /**
     * 阿里云绑定的域名
     */
    @NotBlank(message = "阿里云绑定的域名不能为空", groups = AliyunGroup.class)
    @URL(message = "阿里云绑定的域名格式不正确", groups = AliyunGroup.class)
    private String aliyunDomain;
    /**
     * 阿里云路径前缀
     */
    private String aliyunPrefix;
    /**
     * 阿里云EndPoint
     */
    @NotBlank(message = "阿里云EndPoint不能为空", groups = AliyunGroup.class)
    private String aliyunEndPoint;
    /**
     * 阿里云AccessKeyId
     */
    @NotBlank(message = "阿里云AccessKeyId不能为空", groups = AliyunGroup.class)
    private String aliyunAccessKeyId;
    /**
     * 阿里云AccessKeySecret
     */
    @NotBlank(message = "阿里云AccessKeySecret不能为空", groups = AliyunGroup.class)
    private String aliyunAccessKeySecret;
    /**
     * 阿里云BucketName
     */
    @NotBlank(message = "阿里云BucketName不能为空", groups = AliyunGroup.class)
    private String aliyunBucketName;

    /**
     * 腾讯云绑定的域名
     */
    @NotBlank(message = "腾讯云绑定的域名不能为空", groups = QcloudGroup.class)
    @URL(message = "腾讯云绑定的域名格式不正确", groups = QcloudGroup.class)
    private String qcloudDomain;
    /**
     * 腾讯云路径前缀
     */
    private String qcloudPrefix;
    /**
     * 腾讯云AppId
     */
    @NotNull(message = "腾讯云AppId不能为空", groups = QcloudGroup.class)
    private Integer qcloudAppId;
    /**
     * 腾讯云SecretId
     */
    @NotBlank(message = "腾讯云SecretId不能为空", groups = QcloudGroup.class)
    private String qcloudSecretId;
    /**
     * 腾讯云SecretKey
     */
    @NotBlank(message = "腾讯云SecretKey不能为空", groups = QcloudGroup.class)
    private String qcloudSecretKey;
    /**
     * 腾讯云BucketName
     */
    @NotBlank(message = "腾讯云BucketName不能为空", groups = QcloudGroup.class)
    private String qcloudBucketName;
    /**
     * 腾讯云COS所属地区
     */
    @NotBlank(message = "所属地区不能为空", groups = QcloudGroup.class)
    private String qcloudRegion;
    /**
     * 服务器存储
     */
    @NotBlank(message = "本地存储路径不能为空", groups = DiskGroup.class)
    private String diskPath;
    @NotBlank(message = "本地存储代理服务器不能为空", groups = DiskGroup.class)
    private String proxyServer;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQiniuDomain() {
        return qiniuDomain;
    }

    public void setQiniuDomain(String qiniuDomain) {
        this.qiniuDomain = qiniuDomain;
    }

    public String getQiniuAccessKey() {
        return qiniuAccessKey;
    }

    public void setQiniuAccessKey(String qiniuAccessKey) {
        this.qiniuAccessKey = qiniuAccessKey;
    }

    public String getQiniuSecretKey() {
        return qiniuSecretKey;
    }

    public void setQiniuSecretKey(String qiniuSecretKey) {
        this.qiniuSecretKey = qiniuSecretKey;
    }

    public String getQiniuBucketName() {
        return qiniuBucketName;
    }

    public void setQiniuBucketName(String qiniuBucketName) {
        this.qiniuBucketName = qiniuBucketName;
    }

    public String getQiniuPrefix() {
        return qiniuPrefix;
    }

    public void setQiniuPrefix(String qiniuPrefix) {
        this.qiniuPrefix = qiniuPrefix;
    }

    public String getAliyunDomain() {
        return aliyunDomain;
    }

    public void setAliyunDomain(String aliyunDomain) {
        this.aliyunDomain = aliyunDomain;
    }

    public String getAliyunPrefix() {
        return aliyunPrefix;
    }

    public void setAliyunPrefix(String aliyunPrefix) {
        this.aliyunPrefix = aliyunPrefix;
    }

    public String getAliyunEndPoint() {
        return aliyunEndPoint;
    }

    public void setAliyunEndPoint(String aliyunEndPoint) {
        this.aliyunEndPoint = aliyunEndPoint;
    }

    public String getAliyunAccessKeyId() {
        return aliyunAccessKeyId;
    }

    public void setAliyunAccessKeyId(String aliyunAccessKeyId) {
        this.aliyunAccessKeyId = aliyunAccessKeyId;
    }

    public String getAliyunAccessKeySecret() {
        return aliyunAccessKeySecret;
    }

    public void setAliyunAccessKeySecret(String aliyunAccessKeySecret) {
        this.aliyunAccessKeySecret = aliyunAccessKeySecret;
    }

    public String getAliyunBucketName() {
        return aliyunBucketName;
    }

    public void setAliyunBucketName(String aliyunBucketName) {
        this.aliyunBucketName = aliyunBucketName;
    }

    public String getQcloudDomain() {
        return qcloudDomain;
    }

    public void setQcloudDomain(String qcloudDomain) {
        this.qcloudDomain = qcloudDomain;
    }

    public String getQcloudPrefix() {
        return qcloudPrefix;
    }

    public void setQcloudPrefix(String qcloudPrefix) {
        this.qcloudPrefix = qcloudPrefix;
    }

    public Integer getQcloudAppId() {
        return qcloudAppId;
    }

    public void setQcloudAppId(Integer qcloudAppId) {
        this.qcloudAppId = qcloudAppId;
    }

    public String getQcloudSecretId() {
        return qcloudSecretId;
    }

    public void setQcloudSecretId(String qcloudSecretId) {
        this.qcloudSecretId = qcloudSecretId;
    }

    public String getQcloudSecretKey() {
        return qcloudSecretKey;
    }

    public void setQcloudSecretKey(String qcloudSecretKey) {
        this.qcloudSecretKey = qcloudSecretKey;
    }

    public String getQcloudBucketName() {
        return qcloudBucketName;
    }

    public void setQcloudBucketName(String qcloudBucketName) {
        this.qcloudBucketName = qcloudBucketName;
    }

    public String getQcloudRegion() {
        return qcloudRegion;
    }

    public void setQcloudRegion(String qcloudRegion) {
        this.qcloudRegion = qcloudRegion;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public void setProxyServer(String proxyServer) {
        this.proxyServer = proxyServer;
    }
}
