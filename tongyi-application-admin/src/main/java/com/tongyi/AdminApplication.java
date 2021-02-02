package com.tongyi;

//import com.tongyi.modules.job.config.ScheduleConfig;
//import com.tongyi.config.redis.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author 林佛权
 */
//@CrossOrigin(origins = {"http://127.0.0.1:8000", "null"})
@EnableTransactionManagement
@SpringBootApplication()
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
