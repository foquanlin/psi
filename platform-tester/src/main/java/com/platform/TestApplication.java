package com.platform;

//import com.platform.modules.job.config.ScheduleConfig;
//import com.platform.config.redis.RedisConfig;


import com.platform.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

/**
 * 启动类
 *
 * @author 林佛权
 */
//@CrossOrigin(origins = {"http://127.0.0.1:8000", "null"})
//@EnableCaching
@SpringBootApplication()
//@Import({DynamicDataSourceConfig.class})
public class TestApplication extends SpringApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TestApplication.class, args);
        UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
        String key = String.valueOf(System.currentTimeMillis());
        userService.createUser(new User(key,"cccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        User user = userService.getUser(key);
        System.out.println(user);
//        userService.deleteUser(new User("aaa","ccc"));
    }

}
