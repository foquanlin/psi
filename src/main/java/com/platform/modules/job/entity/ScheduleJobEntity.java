/*
 * 项目名称:platform-plus
 * 类名称:ScheduleJobEntity.java
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

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author 李鹏军
 */
@Data
@TableName("SCHEDULE_JOB")
public class ScheduleJobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务id
     */
    @TableId
    private String jobId;

    /**
     * spring bean名称
     */
    @NotBlank(message = "bean名称不能为空")
    private String beanName;

    /**
     * 方法名
     */
    @NotBlank(message = "方法名称不能为空")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;
}
