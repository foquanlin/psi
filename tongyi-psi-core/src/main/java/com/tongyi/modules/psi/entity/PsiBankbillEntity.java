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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

/**
 * 银行账单实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 */
@Data
@TableName(value = "psi_bankbill",autoResultMap = true)
public class PsiBankbillEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 客户供应商
     */
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
    private Date createDate;
    /**
     * 制单人
     */
    private String createUid;
    /**
     * 负责人
     */
    private String ownerUid;
    /**
     * 备注
     */
    private String memo;
    /**
     * 附件
     */
    private String attachUrls;
}
