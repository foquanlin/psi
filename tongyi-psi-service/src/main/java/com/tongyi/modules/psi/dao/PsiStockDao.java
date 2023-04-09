/*
 * 项目名称:项目名称
 * 类名称:PsiStockDao.java
 * 包名称:com.tongyi.modules.psi.dao
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2019 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.psi.entity.PsiStockEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.CacheNamespace;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 库存Dao
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Mapper
@CacheNamespace(flushInterval = 300000L)//缓存五分钟过期
public interface PsiStockDao extends BaseMapper<PsiStockEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<PsiStockEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<PsiStockEntity> listPage(IPage page, @Param("params") Map<String, Object> params);

    /**
     * 计算库存
     * @param warehouseId
     * @param goodsId
     * @return
     */
    public BigDecimal sumStockBySku(@Param("orderId")String orderId,@Param("warehouseId")String warehouseId,@Param("goodsId")String goodsId,@Param("skuId")String skuId);
}
