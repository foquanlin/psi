/*
 * 项目名称:项目名称
 * 类名称:PsiOrderOperationEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Time;

/**
 * 订单操作日志实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 */
@Data
@TableName(value = "psi_order_operation",autoResultMap = true)
public class PsiOrderOperationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 订单
     */
    private String orderId;
    /**
     * 说明
     */
    private String desc;
    /**
     * 日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
     * 操作人
     */
    private String operationUid;
}
