/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefService.java
 * 包名称:com.platform.modules.act.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.modules.act.entity.ActReProcdefEntity;
import org.activiti.engine.repository.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Service接口
 *
 * @author 李鹏军
 * @date 2019-03-18 09:47:54
 */
public interface ActReProcdefService extends IService<ActReProcdefEntity> {
    /**
     * 通过部署ID读取资源
     *
     * @param id       流程部署标识
     * @param proInsId 流程实例表示
     * @param resType  部署文件类型
     * @return 文件流
     */
    InputStream resourceRead(String id, String proInsId, String resType);

    /**
     * 启动流程实例，通过processDefinitionId
     *
     * @param processDefinitionId
     */
    void startProcessInstanceById(String processDefinitionId);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 删除部署流程
     *
     * @param deploymentId 流程部署标识
     */
    void delete(String deploymentId);

    /**
     * 删除部署流程
     *
     * @param deploymentIds 流程部署标识
     */
    void deleteBatch(String[] deploymentIds);

    /**
     * 根据文件部署工作流
     *
     * @param exportDir 文件地址
     * @param file      上传文件
     * @return 部署信息
     * @throws IOException
     */
    String deploy(String exportDir, MultipartFile file) throws IOException;

    /**
     * 转为模型
     *
     * @param id id
     * @return Model
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
    Model convertToModel(String id) throws UnsupportedEncodingException, XMLStreamException;


    /**
     * 流程挂起和激活
     *
     * @param state 流程状态
     * @param id    流程部署标识
     * @return 操作信息
     */
    String updateState(int state, String id);
}
