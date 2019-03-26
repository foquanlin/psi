/*
 * 项目名称:platform-plus
 * 类名称:FanoutReceiver.java
 * 包名称:com.platform.rabbit.fanout
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/26 11:41    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.rabbit.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author 李鹏军
 */
@Component
@RabbitListener(queues = "fanout.message")
public class FanoutReceiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver message  : " + message);
    }
}
