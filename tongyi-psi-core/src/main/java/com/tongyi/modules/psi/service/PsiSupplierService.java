/*
 * 项目名称:项目名称
 * 类名称:PsiSupplierService.java
 * 包名称:com.tongyi.modules.psi.service
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service;

import com.tongyi.core.IService;
import com.tongyi.modules.psi.entity.PsiSupplierEntity;

/**
 * 供应商Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
public interface PsiSupplierService extends IService<PsiSupplierEntity>{

    /**
     * 设置客户供应商状态
     * @param id
     * @param status
     */
    void supplierStatus(String id, String status);
}
