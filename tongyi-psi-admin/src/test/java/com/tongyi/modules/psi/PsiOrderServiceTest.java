/*
 * 项目名称:项目名称
 * 类名称:PsiOrderServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiOrderService;
import com.tongyi.modules.psi.entity.PsiOrderEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 采购单Service
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiOrderServiceTest {

    @Autowired
    protected PsiOrderService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiOrderEntity item = new PsiOrderEntity();
            item.setId(id);
            item.setNo (id);
            item.setCatalog (id);
            item.setType (id);
            item.setCreateDate (LocalDateTime.now());
            item.setSupplierId (id);
            item.setExpressNo (id);
            item.setCreateUid (id);
            item.setStockStatus (id);
            item.setInvoiceStatus (id);
            item.setPayStatus (id);
            item.setStatus (id);
            item.setMemo (id);
            item.setAttachmentUrls (id);
            item.setSettlementAmount (new BigDecimal(idx));
            item.setOrderAmount (new BigDecimal(idx));
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiOrderEntity item = new PsiOrderEntity();
        item.setId(id);
        item.setNo (id);
        item.setCatalog (id);
        item.setType (id);
        item.setCreateDate (LocalDateTime.now());
        item.setSupplierId (id);
        item.setExpressNo (id);
        item.setCreateUid (id);
        item.setStockStatus (id);
        item.setInvoiceStatus (id);
        item.setPayStatus (id);
        item.setStatus (id);
        item.setMemo (id);
        item.setAttachmentUrls (id);
        item.setSettlementAmount (new BigDecimal(idx));
        item.setOrderAmount (new BigDecimal(idx));
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
