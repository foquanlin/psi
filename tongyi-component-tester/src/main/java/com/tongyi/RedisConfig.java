/*
 * 项目名称:tongyi-component
 * 类名称:RedisConfig.java
 * 包名称:com.tongyi.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi;

//import com.tongyi.common.utils.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Redis配置
 *
 * @author 林佛权
 */
//@Slf4j
@Configuration
@EnableCaching
//@ConditionalOnClass(RedisConnectionFactory.class)
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//@ConditionalOnBean(RedisConnectionFactory.class)
//@ConditionalOnMissingBean(CacheManager.class)
//@Conditional(CacheCondition.class)
public class RedisConfig {
//    @Value("${spring.cache.type}")
//    private String type;
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private Integer port;
//
//    @Value("${spring.redis.timeout}")
//    private String strTimeout;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.database}")
//    private Integer database;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private String strMaxWaitMillis;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private Integer maxIdle;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
    @Value("${spring.redis.expire-time}")
    private Duration expire;

//    @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
//    @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
//    @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
//    @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
//    @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
//    @ConditionalOnNotWebApplication（不是web应用）
//    @ConditionalOnBean(RedisTemplate.class)
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.DEFAULT);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //配置序列化(解决乱码的问题)
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ZERO)
                .entryTtl(expire)      //设置默认缓存15秒
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        RedisCacheManager rcm= RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
        return rcm;
    }
//    //    @RefreshScope
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
//        return cacheManager;
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(factory);
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
//                Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        //序列化设置 ，这样计算是正常显示的数据，也能正常存储和获取
//        redisTemplate.setEnableDefaultSerializer(false);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(factory);
//        return stringRedisTemplate;
//    }

//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//
//        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//         template.setKeySerializer(jackson2JsonRedisSerializer);
//        template.setDefaultSerializer(jackson2JsonRedisSerializer);
//        template.setKeySerializer(jackson2JsonRedisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        return template;
//    }

//    @RefreshScope
//    @Bean
//    public KeyGenerator wiselyKeyGenerator(){
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }


//    @RefreshScope
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
//        template.afterPropertiesSet();
//        return template;
//    }

//    @RefreshScope
//    private void setSerializer(StringRedisTemplate template) {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
//    @Bean
//    public RedisStandaloneConfiguration configuration() {
//        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(host, port);
//        standaloneConfig.setDatabase(database);
//        standaloneConfig.setPassword(password);
//        return standaloneConfig;
//    }
//
////    @RefreshScope
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration configuration) {
//        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
//        factory.setTimeout(Integer.parseInt(strTimeout.replace("ms", ""))); //设置连接超时时间
//        factory.getPoolConfig().setMaxIdle(maxIdle);
//        factory.getPoolConfig().setMinIdle(minIdle);
//        factory.getPoolConfig().setMaxTotal(maxActive);
//        factory.getPoolConfig().setMaxWaitMillis(Long.parseLong(strMaxWaitMillis.replace("ms", "")));
//        return factory;
//    }
//
//    @Bean
//    public JedisPool jedisPool() {
//        long maxWaitMillis = Long.parseLong(strMaxWaitMillis.replace("ms", ""));
//        Integer timeout = Integer.parseInt(strTimeout.replace("ms", ""));
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setTestOnBorrow(true);
//        if (StringUtils.isBlank(password)) {
//            password = null;
//        }
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
//
//        log.info("------------------------------------------------JedisPool注入成功！------------------------------------------------");
//        log.info("redis地址：" + host + ":" + port);
//        return jedisPool;
//    }

//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
//                                          ResourceLoader resourceLoader) {
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults(determineConfiguration(resourceLoader.getClassLoader()));
//        List<String> cacheNames = this.cacheProperties.getCacheNames();
//        if (!cacheNames.isEmpty()) {
//            builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
//        }
//        return this.customizerInvoker.customize(builder.build());
//    }


}
