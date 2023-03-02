package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tongyi.common.exception.BusinessException;
import com.tongyi.core.ModuleExecute;
import com.tongyi.core.ServiceException;
import com.tongyi.modules.psi.entity.PsiAllocationEntity;
import com.tongyi.modules.psi.entity.PsiAllocationGoodsEntity;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import com.tongyi.modules.psi.service.PsiAllocationGoodsService;
import com.tongyi.modules.psi.service.PsiAllocationService;
import com.tongyi.modules.psi.service.PsiStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;

/**
 * 库存调拨
 */
@Slf4j
@Service
public class StockAllocationExecute implements ModuleExecute<PsiAllocationEntity, JsonObject,Void> {
    @Autowired
    private PsiAllocationService psiAllocationService;
    @Autowired
    private PsiAllocationGoodsService psiAllocationGoodsService;

    @Autowired
    private PsiStockService stockService;

    @Override
    public boolean checker(PsiAllocationEntity entity, JsonObject params) {
        String inWarehouseId = entity.getInWarehouseId();
        String outWarehouseId = entity.getOutWarehouseId();
        JsonArray list = params.getAsJsonArray("dataList");
        Objects.requireNonNull(inWarehouseId,"调入仓库不能为空");
        Objects.requireNonNull(outWarehouseId,"调出仓库不能为空");
        Objects.requireNonNull(list,"请选择调库商品");
        if (inWarehouseId.equalsIgnoreCase(outWarehouseId)){
            throw new BusinessException("同一仓库不能调拨");
        }
        if (null == list || list.isEmpty()){
            throw new BusinessException("请选择调库商品");
        }
        /**
         * 检查调出仓库库存是否足够
         */

        Iterator<JsonElement> it = list.iterator();
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            String goodsId = item.get("goodsId").getAsString();
            String skuId = item.get("skuId").getAsString();
            BigDecimal num = item.get("num").getAsBigDecimal();

            BigDecimal stockNum = stockService.stockNum(entity.getOutWarehouseId(),goodsId,skuId);
            if (stockNum.compareTo(num)<0){
                log.info("调拨商品库存不足:warehouseId={},goodsId={},skuId={}",outWarehouseId);
                throw new ServiceException("库存不足!");
            }
        }
        log.info("创建调拨单,数据检查正常:inWarehouseId={},outWarehouseId={},dataList={}",inWarehouseId,outWarehouseId,list);
        return true;
    }
    @Override
    public Void execute(PsiAllocationEntity entity, JsonObject params) throws ServiceException {
        log.info("开始执行调拨:{}",entity);
        JsonArray list = params.getAsJsonArray("dataList");
        psiAllocationService.addEntity(entity);
        Iterator<JsonElement> it = list.iterator();
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            String goodsId = item.get("goodsId").getAsString();
            String skuId = item.get("skuId").getAsString();
            BigDecimal num = item.get("num").getAsBigDecimal();
            String memo = item.get("memo").getAsString();
            PsiAllocationGoodsEntity goods = PsiAllocationGoodsEntity.newEntity(entity.getId(),goodsId,skuId,num,memo);
            psiAllocationGoodsService.addEntity(goods);
            PsiStockEntity out = PsiStockEntity.outStock(PsiStockEntity.Catalog.DIAOBO,entity.getOutWarehouseId(),goodsId,skuId,num,entity.getId());
            PsiStockEntity in = PsiStockEntity.inStock(PsiStockEntity.Catalog.DIAOBO,entity.getInWarehouseId(),goodsId,skuId,num,entity.getId());
            stockService.addEntity(out);
            stockService.addEntity(in);
        };
        log.info("创建调拨单完成:{}",entity);
        return null;
    }
}
