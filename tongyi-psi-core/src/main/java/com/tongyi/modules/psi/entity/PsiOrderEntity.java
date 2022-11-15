/*
 * 项目名称:项目名称
 * 类名称:PsiOrderEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tongyi.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

/**
 * 采购单实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_order",autoResultMap = true)
public class PsiOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 订单编号
     */
    private String no;
    /**
     * 订单分类
     */
    private String catalog;
    /**
     * 订单类型
     */
    private String type;
    /**
     * 订单日期
     */
    private Date createDate;
    /**
     * 客户供应商
     */
    private String supplierId;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 制单人
     */
    private String createUid;
    /**
     * 库存状态
     */
    private String stockStatus;
    /**
     * 发票状态
     */
    private String invoiceStatus;
    /**
     * 付款状态
     */
    private String payStatus;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 附件
     */
    private String attachmentUrls;
    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    @TableField(exist = false)
    private PsiSupplierEntity supplier;
    @TableField(exist = false)
    private SysUserEntity createUser;
}
