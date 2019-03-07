package com.platform;

import com.platform.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * 启动类
 *
 * @author 李鹏军
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class PlatformPlusApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PlatformPlusApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlatformPlusApplication.class);
    }
}
