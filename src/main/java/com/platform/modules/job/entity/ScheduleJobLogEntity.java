/*
 * 项目名称:platform-plus
 * 类名称:ScheduleJobLogEntity.java
 * 包名称:com.platform.modules.job.entity
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.job.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author 李鹏军
 */
@Data
@TableName("SCHEDULE_JOB_LOG")
public class ScheduleJobLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId
    private String logId;

    /**
     * 任务id
     */
    private String jobId;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;
}
