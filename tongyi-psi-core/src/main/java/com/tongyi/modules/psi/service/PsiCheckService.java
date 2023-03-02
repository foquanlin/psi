/*
 * 项目名称:项目名称
 * 类名称:PsiCheckService.java
 * 包名称:com.tongyi.modules.psi.service
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.service;

import com.tongyi.core.IService;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 盘点Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
public interface PsiCheckService extends IService<PsiCheckEntity>{
}
