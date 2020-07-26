package com.platform;

//import com.platform.modules.job.config.ScheduleConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 启动类
 *
 * @author 林佛权
 */
@SpringBootApplication()
//@MapperScan("com.github.niefy.modules.*.dao")
//@ComponentScan(basePackages={"com.platform","com.github"})
//@Import({DynamicDataSourceConfig.class})
public class AdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminApplication.class);
    }
}
