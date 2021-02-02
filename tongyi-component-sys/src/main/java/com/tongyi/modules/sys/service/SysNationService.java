/*
 * 项目名称:项目名称
 * 类名称:SysNationService.java
 * 包名称:com.tongyi.modules.sys.service
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.modules.sys.entity.SysNationEntity;

import java.util.List;
import java.util.Map;

/**
 * 民族Service接口
 *
 * @author 惠州市酷天科技有限公司
 * @date 2021-02-02 20:05:49
 */
public interface SysNationService extends IService<SysNationEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysNationEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询民族
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增民族
     *
     * @param sysNation 民族
     * @return 新增结果
     */
    boolean add(SysNationEntity sysNation);

    /**
     * 根据主键更新民族
     *
     * @param sysNation 民族
     * @return 更新结果
     */
    boolean update(SysNationEntity sysNation);

    /**
     * 根据主键删除民族
     *
     * @param code code
     * @return 删除结果
     */
    boolean delete(String code);

    /**
     * 根据主键批量删除
     *
     * @param codes codes
     * @return 删除结果
     */
    boolean deleteBatch(String[] codes);
}
