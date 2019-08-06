/*
 * 项目名称:platform-plus
 * 类名称:OrderTask.java
 * 包名称:com.platform.modules.job.task
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/7/19 11:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务
 * demoTask为spring bean的名称
 *
 * @author 李鹏军
 */
@Slf4j
@Component("demoTask")
public class DemoTask {

    /**
     * demo定时任务
     */
    @SuppressWarnings(value = "unused")
    @Transactional(rollbackFor = Exception.class)
    public void expireOrder() {
        log.info("--------------------------开始执行demo定时任务--------------------------");

        log.info("--------------------------结束过期demo定时任务--------------------------");
    }
}
