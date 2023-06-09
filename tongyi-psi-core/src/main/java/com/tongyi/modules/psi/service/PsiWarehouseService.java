/*
 * 项目名称:项目名称
 * 类名称:PsiWarehouseService.java
 * 包名称:com.tongyi.modules.psi.service
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service;

import com.tongyi.core.IService;
import com.tongyi.modules.psi.entity.PsiWarehouseEntity;
import org.apache.poi.hpsf.Decimal;

/**
 * 仓库Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
public interface PsiWarehouseService extends IService<PsiWarehouseEntity>{

    void defaultWarehouse(String id);

    void warehouseStatus(String id, String status);
}
