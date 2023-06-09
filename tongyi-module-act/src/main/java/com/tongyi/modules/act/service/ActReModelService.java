/*
 * 项目名称:tongyi-component
 * 类名称:ActReModelService.java
 * 包名称:com.tongyi.modules.act.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 13:33:07        林佛权     初版做成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.modules.act.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tongyi.modules.act.entity.ActReModelEntity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Service接口
 *
 * @author 林佛权
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
     */
    BpmnModel getBpmModel(String id);

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
