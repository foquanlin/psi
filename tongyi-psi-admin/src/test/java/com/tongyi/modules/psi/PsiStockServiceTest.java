/*
 * 项目名称:项目名称
 * 类名称:PsiStockServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiStockService;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Random;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 库存Service
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiStockServiceTest {

    @Autowired
    protected PsiStockService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiStockEntity item = new PsiStockEntity();
            item.setId(id);
            item.setSupplierId (id);
            item.setWarehouseId (id);
            item.setGoodsId (id);
            item.setSkuId (id);
            item.setOrderId (id);
            item.setCatalog (id);
            item.setType (id);
            item.setNum (new BigDecimal(idx));
            item.setBatchNo (id);
            item.setCreateTime (new Date());
            item.setStatus (id);
            item.setCostPrice (new BigDecimal(idx));
            item.setSalePrice (new BigDecimal(idx));
            item.setCreateUid (id);
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiStockEntity item = new PsiStockEntity();
        item.setId(id);
        item.setSupplierId (id);
        item.setWarehouseId (id);
        item.setGoodsId (id);
        item.setSkuId (id);
        item.setOrderId (id);
        item.setCatalog (id);
        item.setType (id);
        item.setNum (new BigDecimal(idx));
        item.setBatchNo (id);
        item.setCreateTime (new Date());
        item.setStatus (id);
        item.setCostPrice (new BigDecimal(idx));
        item.setSalePrice (new BigDecimal(idx));
        item.setCreateUid (id);
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
