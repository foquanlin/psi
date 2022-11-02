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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;
import java.util.Map;

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
    private Date createDate;
    /**
     * 备注
     */
    private String memo;

    public static PsiCheckDetailEntity newEntity(PsiCheckEntity check, PsiCheckDetailEntity item) {
        PsiCheckDetailEntity entity = new PsiCheckDetailEntity();
        entity.setCid(check.getId());
        entity.setWarehouseId(check.getWarehouseId());
        entity.setSkuId(item.getSkuId());
        entity.setGoodsId(item.getGoodsId());
        entity.setBeforeNum(item.getBeforeNum());
        entity.setAfterNum(item.getAfterNum());
        entity.setCreateDate(new Date());
        entity.setMemo(item.getMemo());
        return entity;
    }

    public static PsiCheckDetailEntity newEntity(Map<String, Object> item) {
        PsiCheckDetailEntity entity = new PsiCheckDetailEntity();
        entity.setGoodsId((String)item.get("goodsId"));
        entity.setSkuId((String)item.get("skuId"));
        entity.setBeforeNum(new BigDecimal((Integer) item.get("beforeNum")));
        entity.setAfterNum(new BigDecimal((Integer) item.get("afterNum")));
        return entity;
    }
}
