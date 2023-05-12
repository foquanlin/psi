/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Data
@TableName(value = "psi_goods",autoResultMap = true)
public class PsiGoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 品牌
     */
    private String brandId;
    /**
     * 分类
     */
    private String catalogId;
    /**
     * 商品编码
     */
    private String no;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    /**
     * 单位
     */
    private String unitId;
    /**
     * 图片
     */
    private String picUrls;
    /**
     * 备注
     */
    private String memo;

    /**
     * 商品明细
     */
    @TableField(exist = false)
    private List<PsiGoodsSkuEntity> skuList = new ArrayList<>();

    /**
     * 商品规格
     */
    @TableField(exist = false)
    private List<PsiGoodsSpecEntity> specList = new ArrayList<>();

    /**
     * 库存数量
     */
    @TableField(exist = false)
    private BigDecimal warehouseNum;
    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private String catalogName;
    @TableField(exist = false)
    private String brandName;
    @TableField(exist = false)
    private BigDecimal costPrice;
    @TableField(exist = false)
    private BigDecimal salePrice;
}
