/*
 * 项目名称:platform-plus
 * 类名称:SysDictService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-01-15 11:42:20        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典Service接口
 *
 * @author 李鹏军
 * @date 2019-01-15 11:42:20
 */
public interface SysDictService extends IService<SysDictEntity> {
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysDictEntity> queryAll(Map<String, Object> params);

    /**
     * 查询分页信息
     *
     * @param params 查询参数
     * @return IPage
     */
    IPage queryPage(Map<String, Object> params);

    /**
     * 新增实体
     *
     * @param sysDict 实体
     */
    void add(SysDictEntity sysDict);

    /**
     * 根据主键更新实体
     *
     * @param sysDict 实体
     */
    void update(SysDictEntity sysDict);

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     */
    void deleteBatch(String[] ids);

    /**
     * 根据code查询数据字典
     *
     * @param params 查询参数
     * @return List
     */
    List<SysDictEntity> queryByCode(Map<String, Object> params);
}
