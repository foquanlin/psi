/*
 * 项目名称:platform-plus
 * 类名称:SwaggerConfig.java
 * 包名称:com.platform.config
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.config;

import com.platform.modules.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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
 * @author 李鹏军
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微同软件")
                .description("微同软件APP接口文档")
                .version("1.0.0")
                .contact(new Contact("李鹏军", "https://fly2you.cn/my/u/1", "939961241@qq.com"))
                .build();
    }

    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey("token", "token", "header")
        );
    }
}