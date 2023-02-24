/*
 * 项目名称:项目名称
 * 类名称:PsiOrderExpressEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

/**
 * 运杂费实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_order_express",autoResultMap = true)
public class PsiOrderExpressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 关联订单
     */
    private String orderId;
    /**
     * 费用类型
     */
    private String type;
    /**
     * 成本费用
     */
    private BigDecimal costAmount;
    /**
     * 订单加收费用
     */
    private BigDecimal orderAmount;
    /**
     * 备注
     */
    private String memo;
    /**
     * 日期
     */
    private Date createDate;
}
