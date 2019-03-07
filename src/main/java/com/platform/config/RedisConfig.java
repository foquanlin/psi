/*
 * 项目名称:platform-plus
 * 类名称:RedisConfig.java
 * 包名称:com.platform.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.config;

import com.platform.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Redis配置
 *
 * @author 李鹏军
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.timeout}")
    private String strTimeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private String strMaxWaitMillis;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPool jedisPool() {
        long maxWaitMillis = Long.parseLong(strMaxWaitMillis.replace("ms", ""));
        Integer timeout = Integer.parseInt(strTimeout.replace("ms", ""));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        if (StringUtils.isBlank(password)) {
            password = null;
        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);

        log.info("------------------------------------------------JedisPool注入成功！------------------------------------------------");
        log.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }
}
