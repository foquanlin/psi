/*
 * 项目名称:tongyi-component
 * 类名称:SysMailLogEntity.java
 * 包名称:com.tongyi.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送日志实体
 *
 * @author 林佛权
 */
@Data
@TableName("sys_mail_log")
public class SysMailLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 系统自动邮件
     */
    public static final int SYS_SEND = 0;
    /**
     * 操作人主动邮件
     */
    public static final int USER_SEND = 1;

    /**
     * 系统邮件签名
     */
    public static final String SIGNATURE_STR = "<br><font color='red'>-------------------------------------------------------------------<br>以上内容为邮件系统自动发送，请勿直接回复。</font>";

    /**
     *
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 发送人
     */
    private String sender;
    /**
     * 接收人
     */
    private String receiver;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 0：系统发送邮件 1：用户发送邮件
     */
    private Integer type;
    /**
     * 发送结果 0:发送成功 1:发送失败
     */
    private Integer sendResult;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建者所属机构
     */
    private String createUserOrgNo;
}
