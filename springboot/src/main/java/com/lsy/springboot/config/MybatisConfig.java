package com.lsy.springboot.config;

import com.zaxxer.hikari.util.DriverDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 暂时不用
 */
//@Configuration
public class MybatisConfig {

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(org.apache.ibatis.session.Configuration configuration) throws IOException, SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(new DriverDataSource("jdbc:mysql://localhost:3306/devs"
        ,null,null,"root","Liusiyuan1"));
        // 设置查找器
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        // 手动设置配置
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }
}
