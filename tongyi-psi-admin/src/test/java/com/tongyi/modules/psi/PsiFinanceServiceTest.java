/*
 * 项目名称:进销存
 * 类名称:PsiFinanceServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiFinanceService;
import com.tongyi.modules.psi.entity.PsiFinanceEntity;
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
 * 非销售Service
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-05-24 16:00:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiFinanceServiceTest {

    @Autowired
    protected PsiFinanceService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiFinanceEntity item = new PsiFinanceEntity();
            item.setId(id);
            item.setNo (id);
            item.setType (id);
            item.setSupplierId (id);
            item.setCreateDate (new Date());
            item.setCreateUid (id);
            item.setOwnerUid (id);
            item.setMemo (id);
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiFinanceEntity item = new PsiFinanceEntity();
        item.setId(id);
        item.setNo (id);
        item.setType (id);
        item.setSupplierId (id);
        item.setCreateDate (new Date());
        item.setCreateUid (id);
        item.setOwnerUid (id);
        item.setMemo (id);
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
