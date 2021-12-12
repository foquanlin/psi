package com.tongyi.modules.oss.cloud;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-11-23
 */

/**
 * 云服务商
 */
public enum CloudService {
    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3),
    /**
     * 服务器存储
     */
    DISCK(4),
    /**
     * FastDFS
     */
    FAST_DFS(5);

    private int value;

    CloudService(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}