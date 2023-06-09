/*
 * 项目名称:tongyi-component
 * 类名称:DynamicDataSourceConfig.java
 * 包名称:com.tongyi.datasources
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    林佛权      初版完成
 *
 * Copyright (c) 2019-2019 酷天科技
 */
package com.tongyi.datasources;

//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


/**
 * 配置多数据源
 *
 * @author 林佛权
 */
//@Configuration
public class DynamicDataSourceConfig {

//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.first")
//    public DataSource firstDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.second")
//    public DataSource secondDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }

//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        /*
         * map中2个元素，与2的n次方最接近的数是2，但是这里如果设置容量为2的话 2/2=1,
         * 已经超过默认加载因子(0.75)的大小了。因此会resize一次，变成4。所以最优的值是4。
         *
         * https://www.cnblogs.com/tiancai/p/9558895.html
         */
//        Map<Object, Object> targetDataSources = new HashMap<>(4);
//        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
//        targetDataSources.put(DataSourceNames.SECOND, secondDataSource);
//        return new DynamicDataSource(firstDataSource, targetDataSources);
//    }
}
