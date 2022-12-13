package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 若pom中若添加了druid-spring-boot-starter依赖，则需要排除 DruidDataSourceAutoConfigure.class，否则排除pring Boot 2.0以上默认连接池hikari：DataSourceAutoConfiguration.class
 * DataSourceAutoConfiguration.class 会自动查找 application.yml 或者 properties 文件里的 spring.datasource.* 相关属性并自动配置单数据源「注意这里提到的单数据源」
 * 如果不排除其会在原生的spring.datasource下找url, username, password等。而我们动态数据源的配置路径是变化的，所以需要排除
 * 如果想在项目中使用多数据源就需要排除它，手动指定多数据源
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.lsy.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class,args);
    }
}
