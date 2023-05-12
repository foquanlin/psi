/*
 * 项目名称:项目名称
 * 类名称:PsiCheckDetailEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
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

/**
 * 盘点明细实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@Data
@TableName(value = "psi_check_detail",autoResultMap = true)
public class PsiCheckDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 盘点主单
     */
    private String cid;
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
     * 盘点前数量
     */
    private BigDecimal beforeNum;
    /**
     * 盘点数量
     */
    private BigDecimal afterNum;
    /**
     * 盘点日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    /**
     * 备注
     */
    private String memo;

    @TableField(exist = false)
    private String specName;
    @TableField(exist = false)
    private String specValue;
    @TableField(exist = false)
    private String warehouseName;
    @TableField(exist = false)
    private String goodsName;
    @TableField(exist = false)
    private String unitName;

    public BigDecimal getStockNum(){
        return beforeNum.subtract(afterNum).abs();
    }
    /**
     * 判断盘点前后应该是生成入库单还是出库单
     * @return
     */
    private PsiStockEntity.Type getStockType(){
        if (beforeNum.compareTo(afterNum)>=0){
            return PsiStockEntity.Type.OUT;
        }
        return PsiStockEntity.Type.IN;
    }

    /**
     * 创建盘点明细
     * @param check
     * @param item
     * @return
     */
    public static PsiCheckDetailEntity newEntity(PsiCheckEntity check, PsiCheckDetailEntity item) {
        PsiCheckDetailEntity entity = new PsiCheckDetailEntity();
        entity.setCid(check.getId());
        entity.setWarehouseId(check.getWarehouseId());
        entity.setSkuId(item.getSkuId());
        entity.setGoodsId(item.getGoodsId());
        entity.setBeforeNum(item.getBeforeNum());
        entity.setAfterNum(item.getAfterNum());
        entity.setCreateDate(LocalDate.now());
        entity.setMemo(item.getMemo());
        return entity;
    }

    /**
     * 创建盘点明细
     * @param warehouseId
     * @return
     */
    public static PsiCheckDetailEntity newEntity(String warehouseId, String goodsId,String skuId,BigDecimal beforeNum,BigDecimal afterNum,String memo) {
        PsiCheckDetailEntity entity = new PsiCheckDetailEntity();
        entity.setWarehouseId(warehouseId);
        entity.setGoodsId(goodsId);
        entity.setSkuId(skuId);
        entity.setBeforeNum(beforeNum);
        entity.setAfterNum(afterNum);
        entity.setMemo(memo);
        entity.setCreateDate(LocalDate.now());
        return entity;
    }

    //根据盘点明细创建库存明细
    public PsiStockEntity newCheckStock(String userId,String orderId){
        PsiStockEntity entity = new PsiStockEntity();
        entity.setType(this.getStockType().getCode());
        entity.setCatalog(PsiStockEntity.Catalog.PANDIAN.getCode());
        entity.setStatus(PsiStockEntity.Status.RUN.getCode());
        entity.setWarehouseId(this.getWarehouseId());
        entity.setSupplierId(null);
        entity.setGoodsId(this.getGoodsId());
        entity.setSkuId(this.getSkuId());
        entity.setNum(this.getStockNum());
        entity.setCreateTime(LocalDate.now());
        entity.setCreateUid(userId);
        entity.setOrderId(orderId);
        return entity;
    }
}
