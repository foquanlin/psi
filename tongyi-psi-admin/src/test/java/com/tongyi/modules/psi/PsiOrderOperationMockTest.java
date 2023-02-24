/*
 * 项目名称:项目名称
 * 类名称:PsiOrderOperationMockTest.java
 * 包名称:com.tongyi.modules.psi.Mocktest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.controller.PsiOrderOperationController;
import com.tongyi.modules.psi.entity.PsiOrderOperationEntity;
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
 * 订单操作日志Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiOrderOperationMockTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected PsiOrderOperationController controller; // 把要测试的controller注入进来

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    @Test
    public void testAdd() {
        for(int i=0;i<100;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiOrderOperationEntity item = new PsiOrderOperationEntity();
            item.setId(id);
            item.setOrderId (id);
            item.setDesc (id);
            item.setCreateDate (new Date());
            item.setOperationUid (id);
            controller.save(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiOrderOperationEntity item = new PsiOrderOperationEntity();
        item.setId(id);
        item.setOrderId (id);
        item.setDesc (id);
        item.setCreateDate (new Date());
        item.setOperationUid (id);
        controller.save(item);
        controller.update(item);
        controller.info(id);
        controller.queryAll(new HashMap<>());
        controller.list(1,10,new HashMap<>());
        controller.delete(new String[]{id});
    }
}
