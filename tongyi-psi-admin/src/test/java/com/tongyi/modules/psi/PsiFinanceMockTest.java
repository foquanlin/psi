/*
 * 项目名称:进销存
 * 类名称:PsiFinanceMockTest.java
 * 包名称:com.tongyi.modules.psi.Mocktest
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.controller.PsiFinanceController;
import com.tongyi.modules.psi.entity.PsiFinanceEntity;
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
 * 非销售Controller
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiFinanceMockTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected PsiFinanceController controller; // 把要测试的controller注入进来

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
}
