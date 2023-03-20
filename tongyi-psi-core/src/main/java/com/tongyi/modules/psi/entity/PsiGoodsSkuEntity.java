/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品sku实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
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
     * 状态
     */
    private String status;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 规格值
     */
    private String specValue;

    /**
     * 商品信息
     */
    @TableField(exist = false)
    private PsiGoodsEntity goods;

    /**
     * 库存数据
     */
    @TableField(exist = false)
    private BigDecimal warehouseNum;

    @TableField(exist = false)
    private String goodsName;


    public void reverseStatus(){
        this.status  = Status.valueOf(status)==Status.UP?Status.DOWN.getCode():Status.UP.getCode();
    }

    public static PsiGoodsSkuEntity newEntity(PsiGoodsSpecEntity spec) {
        PsiGoodsSkuEntity entity = new PsiGoodsSkuEntity();
        entity.setStatus(Status.UP.code);
        entity.setGoodsId(spec.getGoodsId());
        entity.setSpecName(spec.getSpecValue());
        return entity;
    }

    public enum Status {
        UP("UP","上架"),
        DOWN("DOWN","下架");

        private String code;
        private String name;

        Status(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
