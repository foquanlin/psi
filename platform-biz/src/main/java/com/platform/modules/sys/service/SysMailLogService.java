/*
 * 项目名称:platform-plus
 * 类名称:SysMailLogService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-21 16:46:32        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.sys.entity.SysMailLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 邮件发送日志Service接口
 *
 * @author 李鹏军
 * @date 2019-03-21 16:46:32
 */
public interface SysMailLogService extends IService<SysMailLogEntity> {

    /**
     * 分页查询邮件发送日志
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增邮件发送日志
     *
     * @param sysMailLog 邮件发送日志
     * @return 新增结果
     */
    boolean add(SysMailLogEntity sysMailLog);

    /**
     * 根据主键删除邮件发送日志
     *
     * @param id id
     * @return 删除结果
     */
    boolean delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     * @return 删除结果
     */
    boolean deleteBatch(String[] ids);
}
