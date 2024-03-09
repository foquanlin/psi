/*
 * 项目名称:项目名称
 * 类名称:PsiStockService.java
 * 包名称:com.tongyi.modules.psi.service
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service;

import com.tongyi.core.IService;
import com.tongyi.core.PageInfo;
import com.tongyi.modules.psi.entity.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 库存Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
public interface PsiStockService extends IService<PsiStockEntity> {

    /**
     * 库存数量
     *
     * @param warehouseId
     * @param goodsId
     * @return
     */
    public BigDecimal stockNum(String warehouseId, String goodsId);

    /**
     * 库存数量
     *
     * @param warehouseId
     * @param goodsId
     * @param skuId
     * @return
     */
    public BigDecimal stockNum(String warehouseId, String goodsId, String skuId);

    public PageInfo<PsiGoodsSkuEntity> selectSkuList(int current, int size, Map<String,Object> params);
}

