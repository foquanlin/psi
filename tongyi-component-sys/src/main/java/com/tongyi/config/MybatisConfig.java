package com.tongyi.config;

import com.tongyi.datascope.DataScopeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-09-08
 */
@Configuration
public class MybatisConfig {

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

}
