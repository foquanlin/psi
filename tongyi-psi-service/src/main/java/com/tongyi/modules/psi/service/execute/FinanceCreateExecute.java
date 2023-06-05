package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonObject;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.ServiceException;
import com.tongyi.modules.psi.entity.*;
import com.tongyi.modules.psi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;

@Slf4j
@Service
public class FinanceCreateExecute implements ModuleExecute<PsiOrderEntity, JsonObject,Void> {
    @Autowired
    private PsiFinanceService psiFinanceService;

    @Autowired
    private PsiCostTypeService costTypeService;

    @Autowired
    private PsiOrderService orderService;
    @Autowired
    private PsiOrderAmountService orderAmountService;
    @Override
    public boolean checker(PsiOrderEntity module, JsonObject params) {
        String orderUid = params.get("orderUid").getAsString();
        String ownerUid = params.get("ownerUid").getAsString();
        String bankId = params.get("bankId").getAsString();
        BigDecimal amount = params.get("amount").getAsBigDecimal();
        PsiCostTypeEntity type = costTypeService.getById(params.get("typeId").getAsString());
        if (StringUtils.isBlank(orderUid)){
            throw new BusinessException("请选择收款方或付款方");
        }
        if (StringUtils.isBlank(bankId)){
            throw new BusinessException("请选择首付款账号");
        }
        if ( null == amount || BigDecimal.ZERO.compareTo(amount)>=0){
            throw new BusinessException("请输入金额");
        }
        if (StringUtils.isBlank(ownerUid)){
            throw new BusinessException("请选择责任人");
        }
        return true;
    }

    @Override
    public Void execute(PsiOrderEntity module, JsonObject params) throws ServiceException {
        String orderUid = params.get("orderUid").getAsString();
        String createUid = params.get("createUid").getAsString();
        String ownerUid = params.get("ownerUid").getAsString();
        String memo = params.get("memo").getAsString();
        String bankId = params.get("bankId").getAsString();
        BigDecimal amount = params.get("amount").getAsBigDecimal();

        PsiCostTypeEntity type = costTypeService.getById(params.get("typeId").getAsString());
        String no = PsiCostTypeEntity.Type.valueOf(type.getType()).newNo();
        module.setNo(no);
        module.setTypeId(type.getId());
        module.setOwnerUid(ownerUid);
        module.setMemo(memo);
        module.setOrderUid(orderUid);
        module.setCreateUid(createUid);
        module.setOrderAmount(amount);

        orderService.addEntity(module);
        PsiOrderAmountEntity entity = PsiOrderAmountEntity.newEntity(module,bankId,amount);
        entity.setType(type.getAmountType().getCode());
        orderAmountService.addEntity(entity);
        // psiFinanceDetailService.addEntity(PsiFinanceDetailEntity.newEntity(module.getId(), module.getCreateUid(), amount,null));
        return null;
    }
}
