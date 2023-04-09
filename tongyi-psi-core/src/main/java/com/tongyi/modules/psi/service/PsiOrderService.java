/*
 * 项目名称:项目名称
 * 类名称:PsiOrderService.java
 * 包名称:com.tongyi.modules.psi.service
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service;

import com.tongyi.core.IService;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;

/**
 * 采购单Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
public interface PsiOrderService extends IService<PsiOrderEntity>{

    /**
     * 增加订单库存
     */
    public void addStock(PsiStockEntity stock);

    /**
     * 修改订单库存
     * @param stock
     */
    public void updateStock(PsiStockEntity stock);
    /**
     * 删除订单库存
     * @param ids
     */
    void deleteStock(String[] ids);
}
