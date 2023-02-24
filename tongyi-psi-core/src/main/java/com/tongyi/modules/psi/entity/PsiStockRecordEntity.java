/*
 * 项目名称:项目名称
 * 类名称:PsiStockRecordEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
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
 * 库存流水实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Data
@TableName(value = "psi_stock_record",autoResultMap = true)
public class PsiStockRecordEntity implements Serializable {
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
     * 商品
     */
    private String goodsId;
    /**
     * skuid
     */
    private String skuId;
    /**
     * 仓库
     */
    private String warehouseId;
    /**
     * 关联单号
     */
    private String purchaseOrderId;
    /**
     * 出入库类型
     */
    private String type;
    /**
     * 日期
     */
    private Date createDate;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * 结余
     */
    private BigDecimal remain;
    /**
     * 状态
     */
    private String status;
    /**
     * 进货价格
     */
    private BigDecimal costPrice;
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    /**
     * 制单人
     */
    private String createUid;
}
