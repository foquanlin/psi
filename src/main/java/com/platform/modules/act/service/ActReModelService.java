/*
 * 项目名称:platform-plus
 * 类名称:ActReModelService.java
 * 包名称:com.platform.modules.act.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.act.entity.ActReModelEntity;
import org.activiti.engine.repository.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Service接口
 *
 * @author 李鹏军
 * @date 2019-03-18 13:33:07
 */
public interface ActReModelService extends IService<ActReModelEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增
     *
     * @param actReModel
     * @return 新增结果
     * @throws UnsupportedEncodingException
     */
    Model add(ActReModelEntity actReModel) throws UnsupportedEncodingException;

    /**
     * 部署工作流模型
     *
     * @param id 模型标识
     * @return 部署信息
     */
    String deploy(String id);

    /**
     * 导出XML
     *
     * @param id       流程模型标识
     * @param response 响应
     */
    void export(String id, HttpServletResponse response);

    /**
     * 根据主键删除
     *
     * @param id id
     */
    void delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     */
    void deleteBatch(String[] ids);
}
