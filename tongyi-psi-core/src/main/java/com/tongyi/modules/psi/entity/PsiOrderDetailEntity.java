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
import com.baomidou.mybatisplus.annotation.TableField;
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
     * skuid
     */
    private String skuId;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * 进货价
     */
    private BigDecimal price;
    /**
     * 入库数量
     */
    @TableField(exist = false)
    private BigDecimal stockNum;
    /**
     * 备注
     */
    private String memo;

    @TableField(exist = false)
    private String goodsName;
    @TableField(exist = false)
    private String goodsPicUrls;
    @TableField(exist = false)
    private String specName;
    @TableField(exist = false)
    private String specValue;
    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private String catalogName;

    public static PsiOrderDetailEntity newEntity(String orderId,String goodsId, String skuId, BigDecimal costPrice, BigDecimal num, BigDecimal stockNum) {
        PsiOrderDetailEntity entity = new PsiOrderDetailEntity();
        entity.setOrderId(orderId);
        entity.setGoodsId(goodsId);
        entity.setSkuId(skuId);
        entity.setPrice(costPrice);
        entity.setNum(num);
        entity.setStockNum(stockNum);
        return entity;
    }

    public PsiStockEntity newStockRecord(PsiStockEntity.Catalog catalog,PsiStockEntity.Type type,String warehouseId) {
        PsiStockEntity stock = PsiStockEntity.newStock(catalog,type,warehouseId,this.goodsId,this.skuId,num,orderId);
        return stock;
    }
}
