package com.tongyi.shiro;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author: create by wangmh
 * @projectName: Shiro_example   从yaml文件中获取url匹配规则
 * @packageName: com.shiro.configuration
 * @description:
 **/
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    private Map<String,String> filterChainDefinitionMap;
}