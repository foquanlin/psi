/*
 * 项目名称:项目名称
 * 类名称:PsiOrderDetailEntity.java
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

/**
 * 订单明细实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_order_detail",autoResultMap = true)
public class PsiOrderDetailEntity implements Serializable {
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
     * 商品
     */
    private String goodsId;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * skuid
     */
    private String skuId;
    /**
     * 进货价
     */
    private BigDecimal price;
    /**
     * 入库数量
     */
    private BigDecimal inStockNum;
    /**
     * 备注
     */
    private String memo;
}
