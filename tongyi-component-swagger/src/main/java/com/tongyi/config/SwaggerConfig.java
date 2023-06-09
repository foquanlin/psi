/*
 * 项目名称:tongyi-component
 * 类名称:SwaggerConfig.java
 * 包名称:com.tongyi.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * Swagger配置
 * Parameter Types
 * OpenAPI 3.0 distinguishes between the following parameter types based on the parameter location. The location is determined by the parameter’s in key, for example, in: query or in: path.
 * <p>
 * path parameters, such as /users/{id}
 * query parameters, such as /users?role=admin
 * header parameters, such as X-MyHeader: Value
 * cookie parameters, which are passed in the Cookie header, such as Cookie: debug=0; csrftoken=BUSe35dohU3O1MZvDCU
 * <p>
 * Data Type
 * allowMultiple=true,  ————表示是数组格式的参数
 * "int",
 * "double",
 * "float",
 * "long",
 * "biginteger",
 * "bigdecimal",
 * "byte",
 * "boolean",
 * "string",
 * "object",
 * "__file"
 * <p>
 * 注意：
 * int、double、float、long、biginteger、bigdecimal、byte、需要给example的值
 *
 * @author 林佛权
 */
@Configuration
@Slf4j
@ConditionalOnClass()
@EnableConfigurationProperties({SwaggerProperties.class, SwaggerContactProperties.class})
@EnableOpenApi
public class SwaggerConfig {
    @Autowired
    private SwaggerProperties swaggerProperties;
    @Autowired
    private SwaggerContactProperties concatProperties;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                .apiInfo(apiInfo())
                .enable(swaggerProperties.isEnable())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDesc())
                .version(swaggerProperties.getVersion())
                .contact(new Contact(concatProperties.getName(),
                        concatProperties.getUrl(),
                        concatProperties.getEmail()))
                .build();
    }

    private List<SecurityScheme> security() {
        return Collections.singletonList(
                new ApiKey("token", "token", "header")
        );
    }
}
