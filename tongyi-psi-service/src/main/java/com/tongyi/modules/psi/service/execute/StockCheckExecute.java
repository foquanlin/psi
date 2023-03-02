package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.common.utils.StringUtils;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.ServiceException;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
import com.tongyi.modules.psi.entity.PsiCheckEntity;
import com.tongyi.modules.psi.entity.PsiWarehouseEntity;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import com.tongyi.modules.psi.service.PsiCheckService;
import com.tongyi.modules.psi.service.PsiStockService;
import com.tongyi.modules.psi.service.PsiWarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 库存盘点
 */
@Slf4j
@Service
public class StockCheckExecute  implements ModuleExecute<PsiCheckEntity, JsonObject,Void> {
    @Autowired
    private PsiCheckService psiCheckService;
    @Autowired
    private PsiWarehouseService warehouseService;
    @Autowired
    private PsiCheckDetailService checkDetailService;
    @Autowired
    private PsiStockService stockService;
    @Override
    public boolean checker(PsiCheckEntity module, JsonObject params) {
        String warehouseId = module.getWarehouseId();
        if (StringUtils.isBlank(warehouseId)){
            throw new BusinessException("请选择盘点仓库");
        }
        JsonArray list  = params.get("dataList").getAsJsonArray();
        if (null == list || list.isEmpty()) {
            throw new BusinessException("请录入盘点明细");
        }
        PsiWarehouseEntity warehouse = warehouseService.getById(warehouseId);
        if (null == warehouse){
            throw new BusinessException("没有这个仓库");
        }
        if (PsiWarehouseEntity.Status.RUN != PsiWarehouseEntity.Status.valueOf(warehouse.getStatus())){
            throw new BusinessException("仓库未启用");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void execute(PsiCheckEntity module, JsonObject params) throws ServiceException {
        JsonArray list  = params.get("dataList").getAsJsonArray();
        List<PsiCheckDetailEntity> details = new ArrayList<>();
        Iterator<JsonElement> it = list.iterator();
        while(it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            String goodsId = item.get("goodsId").getAsString();
            String skuId = item.get("skuId").getAsString();
            BigDecimal beforeNum = item.get("beforeNum").getAsBigDecimal();
            BigDecimal afterNum = item.get("afterNum").getAsBigDecimal();
            String memo2 = item.get("memo").getAsString();
            details.add(module.newDetail(goodsId,skuId,beforeNum,afterNum,memo2));
        }

        psiCheckService.addEntity(module);
        details.forEach(item->{
            item.setCid(module.getId());
            checkDetailService.addEntity(item);
            stockService.addEntity(item.newCheckStock(module.getCreateUid(),module.getNo()));
        });
        return null;
    }
}
