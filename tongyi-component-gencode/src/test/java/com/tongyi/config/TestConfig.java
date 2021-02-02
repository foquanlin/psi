package com.tongyi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

//@Configuration
//@ComponentScan(basePackages = {"com.tongyi.*"})
public class TestConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public PropertiesFactoryBean configProperties() throws Exception{
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        propertiesFactoryBean.setLocations(resolver.getResources("classpath*:application.yml,classpath*:application-dev.yml"));
        return propertiesFactoryBean;
    }

//    @Bean
//    public HikariDataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(driver);
//        dataSource.setJdbcUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
////        dataSource.setMaxActive(30);
////        dataSource.setInitialSize(10);
////        dataSource.setValidationQuery("SELECT 1");
////        dataSource.setTestOnBorrow(true);
//        return dataSource;
//    }

//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager() {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource());
//        return dataSourceTransactionManager;
//    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(HikariDataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.tongyi.*");
        return sqlSessionFactoryBean;
    }

}