/*
 * 项目名称:进销存系统
 * 类名称:PsiBankbillEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行账单实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 */
@Data
public class PsiBankbillEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 客户供应商
     */
    @TableField(exist = false)
    private String supplierId;
    /**
     * 类型
     */
    private String type;
    /**
     * 银行账户
     */
    private String bankId;
    /**
     * 订单编号
     */
    private String no;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 制单人
     */
    private String createUid;
    /**
     * 负责人
     */
    private String ownerUid;
    private BigDecimal inAmount;
    private BigDecimal outAmount;
    @TableField(exist = false)
    private String supplierType;
    @TableField(exist = false)
    private String supplierName;
    @TableField(exist = false)
    private String bankName;
    @TableField(exist = false)
    private String orderNo;
    @TableField(exist = false)
    private String orderId;
    @TableField(exist = false)
    private String orderCatalog;
    @TableField(exist = false)
    private String orderType;
}
