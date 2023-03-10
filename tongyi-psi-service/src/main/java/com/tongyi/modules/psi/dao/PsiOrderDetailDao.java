/*
 * 项目名称:项目名称
 * 类名称:PsiOrderDetailDao.java
 * 包名称:com.tongyi.modules.psi.dao
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2019 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.psi.entity.PsiOrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * 订单明细Dao
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Mapper
@CacheNamespace(flushInterval = 300000L)//缓存五分钟过期
public interface PsiOrderDetailDao extends BaseMapper<PsiOrderDetailEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<PsiOrderDetailEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<PsiOrderDetailEntity> listPage(IPage page, @Param("params") Map<String, Object> params);

    public PsiOrderDetailEntity selectById(@Param("id")String id);

    public List<PsiOrderDetailEntity> selectByOrderId(@Param("orderId")String orderId);
}
