package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonObject;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.ServiceException;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import com.tongyi.modules.psi.service.PsiOrderDetailService;
import com.tongyi.modules.psi.service.PsiOrderService;
import com.tongyi.modules.psi.service.PsiStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 删除采购单
 */
@Slf4j
@Service
public class BuyOrderDeleteExecute  implements ModuleExecute<PsiOrderEntity, JsonObject,Void> {
    @Autowired
    private PsiStockService stockService;
    @Autowired
    private PsiOrderService orderService;
    @Autowired
    private PsiOrderDetailService orderDetailService;
    @Override
    public boolean checker(PsiOrderEntity module, JsonObject params) {
        if (null == module){
            throw new BusinessException("请选择采购单");
        }
        return true;
    }

    @Override
    public Void execute(PsiOrderEntity module, JsonObject params) throws ServiceException {
        orderService.deleteEntity(module.getId());
        return null;
    }
}
