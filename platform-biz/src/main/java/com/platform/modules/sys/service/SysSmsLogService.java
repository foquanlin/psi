/*
 * 项目名称:platform-plus
 * 类名称:SysSmsLogService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-02-12 09:51:15        李鹏军     初版做成
 *
 * Copyright (c) 2018-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.SysSmsLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 短信发送日志Service接口
 *
 * @author 李鹏军
 * @date 2019-02-12 09:51:15
 */
public interface SysSmsLogService extends IService<SysSmsLogEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<SysSmsLogEntity> queryAll(Map<String, Object> params);

    /**
     * 查询分页信息
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增实体
     *
     * @param sysSmsLog 实体
     */
    void add(SysSmsLogEntity sysSmsLog);

    /**
     * 根据主键更新实体
     *
     * @param sysSmsLog 实体
     */
    void update(SysSmsLogEntity sysSmsLog);

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     */
    void deleteBatch(String[] ids);

    /**
     * 发送短信
     *
     * @param smsLog smsLog
     * @return SysSmsLogEntity
     */
    SysSmsLogEntity sendSms(SysSmsLogEntity smsLog);
}
