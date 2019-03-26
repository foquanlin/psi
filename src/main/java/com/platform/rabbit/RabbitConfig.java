/*
 * 项目名称:platform-plus
 * 类名称:RabbitConfig.java
 * 包名称:com.platform.rabbit
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/26 11:41    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfig
 *
 * @author 李鹏军
 */
@Configuration
public class RabbitConfig {
    private final static String FANOUT = "fanout.message";

    @Bean
    public Queue fanoutMessage() {
        return new Queue(FANOUT);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue fanoutMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutMessage).to(fanoutExchange);
    }
}
