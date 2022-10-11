/*
 * 项目名称:项目名称
 * 类名称:PsiCheckDetailServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiCheckDetailService;
import com.tongyi.modules.psi.entity.PsiCheckDetailEntity;
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
 * 盘点明细Service
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiCheckDetailServiceTest {

    @Autowired
    protected PsiCheckDetailService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiCheckDetailEntity item = new PsiCheckDetailEntity();
            item.setId(id);
            item.setWarehouseId (id);
            item.setGoodsId (id);
            item.setBeforeNum (new BigDecimal(idx));
            item.setAfterNum (new BigDecimal(idx));
            item.setCreateDate (new Date());
            item.setMemo (id);
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiCheckDetailEntity item = new PsiCheckDetailEntity();
        item.setId(id);
        item.setWarehouseId (id);
        item.setGoodsId (id);
        item.setBeforeNum (new BigDecimal(idx));
        item.setAfterNum (new BigDecimal(idx));
        item.setCreateDate (new Date());
        item.setMemo (id);
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
