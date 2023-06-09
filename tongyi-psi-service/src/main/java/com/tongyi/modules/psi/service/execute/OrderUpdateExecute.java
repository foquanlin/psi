package com.tongyi.modules.psi.service.execute;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;

/**
 * 修改采购单
 */
@Slf4j
@Service
public class OrderUpdateExecute implements ModuleExecute<PsiOrderEntity, JsonObject,Void> {
    @Autowired
    private PsiOrderService orderService;
    @Autowired
    private PsiOrderDetailService orderDetailService;
    @Autowired
    private PsiGoodsSkuService goodsSkuService;
    @Autowired
    private PsiGoodsService goodsService;

    @Autowired
    private PsiBankService bankService;
    @Autowired
    private PsiWarehouseService warehouseService;
    /**
     * 检查采购单数据,如果数据不合规则抛出异常
     * 1.自动生成采购单号
     * 2.检查供应商
     * 3.检查责任人
     * 4.检查采购商品,进价,订购数量,入库仓库,入库数量
     * 5.检查入库仓库是否有效
     * @param module
     * @param params
     * @return
     */
    @Override
    public boolean checker(PsiOrderEntity module, JsonObject params) {
        LocalDate createDate = LocalDate.parse(params.get("createDate").getAsString());
        BigDecimal orderAmount = BigDecimal.ZERO;
        String expressNo = params.get("expressNo").getAsString();
        String orderUid = params.get("orderUid").getAsString();
        String ownerUid = params.get("ownerUid").getAsString();
        String createUid = params.get("userId").getAsString();
        String memo = params.get("memo").getAsString();
        JsonArray list = params.getAsJsonArray("dataList");
        Objects.requireNonNull(list,"请选择采购商品");
        String userId = params.get("userId").getAsString();
        if (StringUtils.isBlank(userId)){
            throw new BusinessException("制单人不能为空");
        }
        Iterator<JsonElement> it = list.iterator(); // 检查选择的商品是否合法
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            String id = item.get("id").getAsString();
            BigDecimal num = item.get("num").getAsBigDecimal();
            BigDecimal price = item.get("price").getAsBigDecimal();
            String goodsId = item.get("goodsId").getAsString();
            String skuId = item.get("skuId").getAsString();

            if(BigDecimal.ZERO.compareTo(num)>=0){
                throw new BusinessException("采购数量必须大于1");
            }
            PsiGoodsEntity goods = goodsService.getById(goodsId);
            if (null == goods){
                throw new BusinessException("所选商品不在商品清单中");
            }
            PsiGoodsSkuEntity sku = goodsSkuService.getById(skuId);
            if (null == sku){
                throw new BusinessException("没有此商品规格");
            }
        }

        module.setOrderAmount(orderAmount);
        module.setCreateDate(createDate);
        module.setExpressNo(expressNo);
        module.setOwnerUid(ownerUid);
        module.setCreateUid(createUid);
        module.setOrderUid(orderUid);
        module.setMemo(memo);
        return true;
    }

    /**
     * 保存采购单,并根据入库数量入库明细
     * @param module
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public Void execute(PsiOrderEntity module, JsonObject params) throws ServiceException {
        String createUid = params.get("userId").getAsString();
        JsonArray list = params.getAsJsonArray("dataList");
        Iterator<JsonElement> it = list.iterator();
        BigDecimal total = BigDecimal.ZERO;
        while (it.hasNext()){
            JsonObject item = it.next().getAsJsonObject();
            String id = item.get("id").getAsString();
            BigDecimal num = item.get("num").getAsBigDecimal();
            BigDecimal price = item.get("price").getAsBigDecimal();
            String goodsId = item.get("goodsId").getAsString();
            String skuId = item.get("skuId").getAsString();
            total = total.add(price.multiply(num));
            PsiOrderDetailEntity detail = PsiOrderDetailEntity.newEntity(module.getId(),goodsId,skuId,price,num,BigDecimal.ZERO);
            detail.setId(id);
            if (StringUtils.isBlank(id)) {
                orderDetailService.addEntity(detail);
            }else{
                orderDetailService.updateEntity(detail);
            }
        }
        module.setOrderAmount(total);
        orderService.updateEntity(module);
        return null;
    }
}
