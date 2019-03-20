/*
 * 项目名称:platform-plus
 * 类名称:ActivitiConfig.java
 * 包名称:com.platform.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/15 09:58    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.config;

import org.activiti.engine.*;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 工作流配置
 *
 * @author 李鹏军
 */
@Configuration
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    /**
     * 解決工作流生成图片乱码问题
     *
     * @param processEngineConfiguration processEngineConfiguration
     */
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
    }

    /**
     * 核心流程引擎类
     *
     * @param transactionManager transactionManager
     * @param dataSource         dataSource
     * @return ProcessEngine
     */
    @Bean
    public ProcessEngine processEngine(PlatformTransactionManager transactionManager, DataSource dataSource) throws IOException {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        //自动部署已有的流程文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceLoader.CLASSPATH_URL_PREFIX + "processes/*.bpmn");
        processEngineConfiguration.setDeploymentResources(resources);
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        return processEngineConfiguration.buildProcessEngine();
    }

    /**
     * 流程仓库Service，用于管理流程仓库，例如部署、删除、读取流程资源
     *
     * @param processEngine processEngine
     * @return RepositoryService
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * 运行时Service，可以也拿过来处理所有正在运行状态的流程实例、任务等
     *
     * @param processEngine processEngine
     * @return RuntimeService
     */
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    /**
     * 任务Service，用于管理和查询任务，例如签收、办理、指派等
     *
     * @param processEngine processEngine
     * @return TaskService
     */
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * 历史Service，用于查询所有历史数据，例如流程实例、任务、活动、变量、附件
     *
     * @param processEngine processEngine
     * @return HistoryService
     */
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    /**
     * 引擎管理Service，和具体业务无关，主要可以查询引擎配置、数据库、作业等
     *
     * @param processEngine processEngine
     * @return ManagementService
     */
    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    /**
     * 身份Service，用于管理和查询用户、组之间的关系
     *
     * @param processEngine processEngine
     * @return IdentityService
     */
    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    /**
     * 表单Service，用于读取流程、任务相关的表单数据
     *
     * @param processEngine processEngine
     * @return FormService
     */
    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    /**
     * 一个新增的服务，用于动态修改流程中的一些参数信息等，是引擎中的一个辅助的服务
     *
     * @param processEngine processEngine
     * @return DynamicBpmnService
     */
    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }
}
