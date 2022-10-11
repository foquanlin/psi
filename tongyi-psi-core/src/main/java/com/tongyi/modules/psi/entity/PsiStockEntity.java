/*
 * 项目名称:项目名称
 * 类名称:PsiStockEntity.java
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
 * 库存实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_stock",autoResultMap = true)
public class PsiStockEntity implements Serializable {
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
     * 仓库
     */
    private String warehouseId;
    /**
     * 商品
     */
    private String goodsId;
    /**
     * skuid
     */
    private String skuId;
    /**
     * 关联单号
     */
    private String orderId;
    /**
     * 出入库分类
     */
    private String catalog;
    /**
     * 出入库类型
     */
    private String type;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * 批次
     */
    private String batchNo;
    /**
     * 时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 平均进价
     */
    private BigDecimal costPrice;
    /**
     * 平均售价
     */
    private BigDecimal salePrice;
    /**
     * 操作人
     */
    private String createUid;
}
