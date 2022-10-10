package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 若pom中若添加了druid-spring-boot-starter依赖，则需要排除 DruidDataSourceAutoConfigure.class，否则排除pring Boot 2.0以上默认连接池hikari：DataSourceAutoConfiguration.class
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.lsy.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class,args);
    }
}
