/*
 * 项目名称:项目名称
 * 类名称:PsiGoodsSkuServiceTest.java
 * 包名称:com.tongyi.modules.psi.ServiceTest
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi;
import com.tongyi.modules.psi.service.PsiGoodsSkuService;
import com.tongyi.modules.psi.entity.PsiGoodsSkuEntity;
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
 * 商品skuService
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-25 21:39:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("junit")
public class PsiGoodsSkuServiceTest {

    @Autowired
    protected PsiGoodsSkuService service; // 把要测试的service注入进来

    @Before
    public void setup() throws Exception {
    }
    @Test
    public void testAdd() {
        for(int i=0;i<10;i++) {
            int idx = new Random().nextInt(1000000);
            String id = "id-" + idx;
            PsiGoodsSkuEntity item = new PsiGoodsSkuEntity();
            item.setId(id);
            item.setGoodsId (id);
            item.setNo (id);
            item.setBarcode (id);
            item.setCostPrice (new BigDecimal(idx));
            item.setSalePrice (new BigDecimal(idx));
            item.setStatus (id);
            item.setSpecName (id);
            item.setSpecValue (id);
            service.addEntity(item);
        }
    }

    @Test
    public void testAll() {
        int idx = new Random().nextInt(1000000);
        String id = "id-" + idx;
        PsiGoodsSkuEntity item = new PsiGoodsSkuEntity();
        item.setId(id);
        item.setGoodsId (id);
        item.setNo (id);
        item.setBarcode (id);
        item.setCostPrice (new BigDecimal(idx));
        item.setSalePrice (new BigDecimal(idx));
        item.setStatus (id);
        item.setSpecName (id);
        item.setSpecValue (id);
        service.addEntity(item);
        service.updateEntity(item);
        service.getById(id);
        service.listAll(new HashMap<>());
        service.listPage(1,10,new HashMap<>());
        service.deleteBatch(new String[]{id});
    }
}
