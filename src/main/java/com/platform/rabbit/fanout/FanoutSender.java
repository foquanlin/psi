/*
 * 项目名称:platform-plus
 * 类名称:FanoutSender.java
 * 包名称:com.platform.rabbit.fanout
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/26 11:41    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.rabbit.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author 李鹏军
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "Hello World, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
    }
}