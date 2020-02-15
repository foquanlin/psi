/*
 * 项目名称:platform-plus
 * 类名称:SysSmsLogEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-02-12 09:51:15        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信发送日志实体
 *
 * @author 李鹏军
 * @date 2019-02-12 09:51:15
 */
@Data
@TableName("SYS_SMS_LOG")
public class SysSmsLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 操作人ID
     */
    private String userId;
    /**
     * 必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    private String content;
    /**
     * 必填参数。手机号码。多个以英文逗号隔开
     */
    private String mobile;
    /**
     * 可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    private Date stime;
    /**
     * 必填参数。用户签名
     */
    private String sign;
    /**
     * 必填参数。固定值 pt
     */
    private String type;
    /**
     * 可选参数。扩展码，用户定义扩展码，只能为数字
     */
    private String extno;
    /**
     * 1成功 0失败
     */
    private Integer sendStatus;
    /**
     * 发送编号
     */
    private String sendId;
    /**
     * 无效号码数
     */
    private Integer invalidNum;
    /**
     * 成功提交数
     */
    private Integer successNum;
    /**
     * 黑名单数
     */
    private Integer blackNum;
    /**
     * 返回消息
     */
    private String returnMsg;
}
