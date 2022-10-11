/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuEntity.java
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

/**
 * 商品sku实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Data
@TableName(value = "psi_goods_sku",autoResultMap = true)
public class PsiGoodsSkuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 仓库
     */
    private String warehouseId;
    /**
     * 商品
     */
    private String goodsId;
    /**
     * 商品编码
     */
    private String no;
    /**
     * 条形码
     */
    private String barcode;
    /**
     * 进货价格
     */
    private BigDecimal costPrice;
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    /**
     * 数量
     */
    private BigDecimal num;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 规格值
     */
    private String specValue;
}
