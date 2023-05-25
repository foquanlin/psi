package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.ServiceException;
import com.tongyi.modules.psi.entity.*;
import com.tongyi.modules.psi.service.PsiCostTypeService;
import com.tongyi.modules.psi.service.PsiFinanceDetailService;
import com.tongyi.modules.psi.service.PsiFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;

@Slf4j
@Service
public class FinanceCreateExecute implements ModuleExecute<PsiFinanceEntity, JsonObject,Void> {
    @Autowired
    private PsiFinanceService psiFinanceService;
    @Autowired
    private PsiFinanceDetailService psiFinanceDetailService;
    @Autowired
    private PsiCostTypeService costTypeService;
    @Override
    public boolean checker(PsiFinanceEntity module, JsonObject params) {
        String supplierId = params.get("supplierId").getAsString();
        String ownerUid = params.get("ownerUid").getAsString();
        PsiCostTypeEntity type = costTypeService.getById(params.get("typeId").getAsString());
        if (StringUtils.isBlank(supplierId)){
            throw new BusinessException("请选择收款方或付款方");
        }
        if (StringUtils.isBlank(ownerUid)){
            throw new BusinessException("请选择责任人");
        }
        JsonArray list = params.getAsJsonArray("dataList");
        Iterator<JsonElement> it = list.iterator(); // 检查选择的商品是否合法
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            BigDecimal amount = item.get("amount").getAsBigDecimal();
            if(BigDecimal.ZERO.compareTo(amount)>=0){
                throw new BusinessException("金额必须大于0");
            }
        }
        return true;
    }

    @Override
    public Void execute(PsiFinanceEntity module, JsonObject params) throws ServiceException {
        String supplierId = params.get("supplierId").getAsString();
        String ownerUid = params.get("ownerUid").getAsString();
        String memo = params.get("memo").getAsString();
        PsiCostTypeEntity type = costTypeService.getById(params.get("typeId").getAsString());
        String no = PsiCostTypeEntity.Type.valueOf(type.getType()).newNo();
        module.setNo(no);
        module.setOwnerUid(ownerUid);
        module.setMemo(memo);
        module.setTypeId(type.getId());
        module.setSupplierId(supplierId);
        JsonArray list = params.getAsJsonArray("dataList");

        psiFinanceService.addEntity(module);

        Iterator<JsonElement> it = list.iterator(); // 检查选择的商品是否合法
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            BigDecimal amount = item.get("amount").getAsBigDecimal();
            String memo2 = item.get("memo").getAsString();
            psiFinanceDetailService.addEntity(PsiFinanceDetailEntity.newEntity(module.getId(), module.getCreateUid(), amount,memo2));
        }
        return null;
    }
}
