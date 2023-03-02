/*
 * 项目名称:项目名称
 * 类名称:PsiAllocationGoodsEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
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
 * 调拨单明细实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:54
 */
@Data
@TableName(value = "psi_allocation_goods",autoResultMap = true)
public class PsiAllocationGoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 调拨单
     */
    private String allocationId;
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
     * 备注
     */
    private String memo;
}
