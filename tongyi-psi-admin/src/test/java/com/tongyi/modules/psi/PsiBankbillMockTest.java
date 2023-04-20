/*
 * 项目名称:进销存系统
 * 类名称:PsiBankbillMockTest.java
 * 包名称:com.tongyi.modules.psi.Mocktest
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.controller.PsiBankbillController;
import com.tongyi.modules.psi.entity.PsiBankbillEntity;
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

import java.util.HashMap;
import java.util.Random;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 银行账单Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 02:05:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiBankbillMockTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected PsiBankbillController controller; // 把要测试的controller注入进来

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    @Test
    public void testAdd() {
        for(int i=0;i<100;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiBankbillEntity item = new PsiBankbillEntity();
            item.setId(id);
            item.setSupplierId (id);
            item.setType (id);
            item.setBankId (id);
            item.setNo (id);
            item.setAmount (new BigDecimal(idx));
            item.setCreateDate (new Date());
            item.setCreateUid (id);
            item.setOwnerUid (id);
            item.setMemo (id);
            item.setAttachUrls (id);
            controller.save(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiBankbillEntity item = new PsiBankbillEntity();
        item.setId(id);
        item.setSupplierId (id);
        item.setType (id);
        item.setBankId (id);
        item.setNo (id);
        item.setAmount (new BigDecimal(idx));
        item.setCreateDate (new Date());
        item.setCreateUid (id);
        item.setOwnerUid (id);
        item.setMemo (id);
        item.setAttachUrls (id);
        controller.save(item);
        controller.update(item);
        controller.info(id);
        controller .listAll(new HashMap<>());
        controller.list(1,10,new HashMap<>());
        controller.delete(new String[]{id});
    }
}
