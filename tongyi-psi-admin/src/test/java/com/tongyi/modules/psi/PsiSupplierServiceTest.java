/*
 * 项目名称:项目名称
 * 类名称:PsiSupplierServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiSupplierService;
import com.tongyi.modules.psi.entity.PsiSupplierEntity;
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
 * 供应商Service
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiSupplierServiceTest {

    @Autowired
    protected PsiSupplierService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiSupplierEntity item = new PsiSupplierEntity();
            item.setId(id);
            item.setName (id);
            item.setCompanyName (id);
            item.setContacts(id);
            item.setPhone (id);
            item.setEmail (id);
            item.setAddress (id);
            item.setMemo (id);
            item.setWeight (idx);
            item.setStatus (id);
            item.setType (id);
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiSupplierEntity item = new PsiSupplierEntity();
        item.setId(id);
        item.setName (id);
        item.setCompanyName (id);
        item.setContacts (id);
        item.setPhone (id);
        item.setEmail (id);
        item.setAddress (id);
        item.setMemo (id);
        item.setWeight (idx);
        item.setStatus (id);
        item.setType (id);
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
