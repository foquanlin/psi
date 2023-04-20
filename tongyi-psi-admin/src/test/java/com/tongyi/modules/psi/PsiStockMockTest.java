/*
 * 项目名称:项目名称
 * 类名称:PsiStockMockTest.java
 * 包名称:com.tongyi.modules.psi.Mocktest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.controller.PsiStockController;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 库存Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiStockMockTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected PsiStockController controller; // 把要测试的controller注入进来

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    @Test
    public void testAdd() {
        for(int i=0;i<100;i++) {
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
            item.setCreateTime (LocalDate.now());
            item.setStatus (id);
            item.setCostPrice (new BigDecimal(idx));
            item.setSalePrice (new BigDecimal(idx));
            item.setCreateUid (id);
            controller.save(item);
        }
    }

}
