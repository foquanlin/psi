/*
 * 项目名称:platform-plus
 * 类名称:ActReProcdefServiceImpl.java
 * 包名称:com.platform.modules.act.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-03-18 09:47:54        李鹏军     初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.act.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.platform.common.exception.BusinessException;
import com.platform.common.utils.Constant;
import com.platform.common.utils.Query;
import com.platform.modules.act.dao.ActReProcdefDao;
import com.platform.modules.act.entity.ActReProcdefEntity;
import com.platform.modules.act.service.ActReProcdefService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * Service实现类
 *
 * @author 李鹏军
 * @date 2019-03-18 09:47:54
 */
@Service("actReProcdefService")
public class ActReProcdefServiceImpl extends ServiceImpl<ActReProcdefDao, ActReProcdefEntity> implements ActReProcdefService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    @Override
    public InputStream resourceRead(String id, String proInsId, String resType) {
        if (StringUtils.isBlank(id)) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
            id = processInstance.getProcessDefinitionId();
        }
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();

        String resourceName = "";
        if (Constant.IMAGE.equals(resType)) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (Constant.XML.equals(resType)) {
            resourceName = processDefinition.getResourceName();
        }

        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }

    @Override
    public void startProcessInstanceById(String processDefinitionId) {
        runtimeService.startProcessInstanceById(processDefinitionId);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        int currPage = Integer.parseInt((String) params.get("page"));
        int limit = Integer.parseInt((String) params.get("limit"));
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().latestVersion().orderByProcessDefinitionKey().asc();

        String category = params.get("category").toString();
        if (!"".equals(category)) {
            processDefinitionQuery.processDefinitionCategory(category);
        }
        String key = params.get("key").toString();
        if (!"".equals(key)) {
            processDefinitionQuery.processDefinitionKey(key);
        }
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage((currPage - 1) * limit, limit);

        List<ActReProcdefEntity> list = Lists.newArrayList();

        for (ProcessDefinition processDefinition : processDefinitionList) {
            ActReProcdefEntity entity = new ActReProcdefEntity();
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            entity.setId(processDefinition.getId());
            entity.setKey(processDefinition.getKey());
            entity.setName(deployment == null ? "" : StringUtils.isBlank(deployment.getName()) ? processDefinition.getName() : deployment.getName());
            entity.setDeployTime(deployment == null ? null : deployment.getDeploymentTime());
            entity.setDeploymentId(processDefinition.getDeploymentId());
            entity.setSuspensionState(processDefinition.isSuspended() ? Constant.TWO : Constant.ONE);
            entity.setResourceName(processDefinition.getResourceName());
            entity.setDgrmResourceName(processDefinition.getDiagramResourceName());
            entity.setCategory(processDefinition.getCategory());
            entity.setVersion(processDefinition.getVersion());
            entity.setDescription(processDefinition.getDescription());
            entity.setEngineVersion(processDefinition.getEngineVersion());
            entity.setTenantId(processDefinition.getTenantId());
            list.add(entity);
        }

        Page<ActReProcdefEntity> page = new Query<ActReProcdefEntity>(params).getPage();
        page.setTotal(processDefinitionQuery.count());
        return page.setRecords(list);
    }

    @Override
    public void delete(String id) {
        repositoryService.deleteDeployment(id, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] deploymentIds) {
        for (String deploymentId : deploymentIds) {
            repositoryService.deleteDeployment(deploymentId, false);
        }
    }

    @Override
    public String deploy(String exportDir, MultipartFile file) throws IOException {
        StringBuilder message = new StringBuilder();

        String fileName = file.getOriginalFilename();

        InputStream fileInputStream = file.getInputStream();
        Deployment deployment = null;
        String extension = FilenameUtils.getExtension(fileName);
        if (Constant.ZIP.equals(extension) || Constant.BAR.equals(extension)) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
        } else if (Constant.PNG.equals(extension)) {
            deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        } else if (fileName.indexOf(Constant.BPMN20) != -1) {
            deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        } else if (Constant.BPMN.equals(extension)) {
            deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        } else {
            message = new StringBuilder("不支持的文件类型：" + extension);
        }

        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

        // 设置流程分类
        for (ProcessDefinition processDefinition : list) {
            repositoryService.setProcessDefinitionCategory(processDefinition.getId(), processDefinition.getCategory());
            message.append("部署成功，流程ID=").append(processDefinition.getId()).append("<br/>");
        }

        if (list.size() == 0) {
            message = new StringBuilder("部署失败，没有流程。");
        }
        return message.toString();
    }

    @Override
    public Model convertToModel(String id) throws UnsupportedEncodingException, XMLStreamException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getCategory());
        modelData.setDeploymentId(processDefinition.getDeploymentId());
        modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count() + 1)));

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);

        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
        return modelData;
    }

    @Override
    public String updateState(int state, String id) {
        String msg = "无操作";
        if (state == Constant.ONE) {
            try {
                repositoryService.activateProcessDefinitionById(id, true, null);
            } catch (Exception e) {
                throw new BusinessException("流程已经激活");
            }
            msg = "已激活ID为[" + id + "]的流程定义。";
        } else if (state == Constant.TWO) {
            try {
                repositoryService.suspendProcessDefinitionById(id, true, null);
            } catch (Exception e) {
                throw new BusinessException("流程已经挂起");
            }
            msg = "已挂起ID为[" + id + "]的流程定义。";
        }
        return msg;
    }
}
