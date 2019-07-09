/*
 * 项目名称:platform-plus
 * 类名称:SysMailLogEntity.java
 * 包名称:com.platform.modules.sys.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送日志实体
 *
 * @author 李鹏军
 * @date 2019-03-21 16:46:32
 */
@Data
@TableName("SYS_MAIL_LOG")
public class SysMailLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
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
