/*
 * 项目名称:项目名称
 * 类名称:PsiCatalogDao.java
 * 包名称:com.tongyi.modules.psi.dao
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 * Copyright (c) 2019-2019 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tongyi.modules.psi.entity.PsiCatalogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * 商品分类Dao
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:55
 */
@Mapper
@CacheNamespace(flushInterval = 300000L)//缓存五分钟过期
public interface PsiCatalogDao extends BaseMapper<PsiCatalogEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<PsiCatalogEntity> listAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<PsiCatalogEntity> listPage(IPage page, @Param("params") Map<String, Object> params);
}
