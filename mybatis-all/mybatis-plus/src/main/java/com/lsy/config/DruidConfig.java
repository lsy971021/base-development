//package com.lsy.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * pom 加 druid 或 druid-spring-boot-starter是才能用
// * 使用druid配置文件时开启
// */
////@Configuration
//public class DruidConfig {
//
//    /**
//     * 需要程序员自己为 DruidDataSource 绑定全局配置文件中的参数，再添加到容器中，而不再使用 Spring Boot 的自动生成了
//     * 是yml里的配置生效
//     * 常用一些配置，可添加在yml中
//     * name		配置这个属性的意义在于没如果存在多个数据源，监控的时候可以通过名字来区分开来。如果没有配置，将会生成一个名字，格式是"DataSource-"+System.identityHashCode(this)
//     * jdbcUrl		连接数据库的url，不同数据库不一样
//     * username		连接数据库的用户名
//     * password		连接数据库的密码。如果你不希望密码直接写在配置文件中
//     * driverClassName		这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName(建议配置下)
//     * initialSize		初始化时建立物理连接的个数，初始化发生在显示调用init方法，或者第一次getConnection时
//     * maxActive		最大连接池数量
//     * maxIdle		已经不再使用，配置了也没效果
//     * minIdle		最小连接池数量
//     * maxWait		获取连接时最大等待时间，单位毫秒，配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁
//     * poolPreparedStatements		是否缓存preparedStatement，也就是PsCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭
//     * maxOpenPreparedStatements		要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置打一下，比如说100
//     * validationQuery		用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn 、testWhileIdle都不会起作用
//     * testOnConnect    在连接初始化时执行连接可用性检查
//     * testOnBorrow		申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
//     * testOnReturn		归还连接时执行它validationQuery检测连接是否有效，做了这个配置会降低性能
//     * testWhileIdle		建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunMills，执行validationQuery检测连接是否有效
//     * timeBetweenEvictionRunMillis		有两个含义:Destory线程会检测连接的间隔时间 testWhileIdle的判断依据，详细看testWhileIdele属性的说明
//     * numTestsPerEvictionRun		不再使用，一个DruidDataSource只支持一个EvicationRun
//     * minEvictableIdleTimeMillis
//     * connectionInitSqls		物理连接初始化的时候执行sql
//     * exceptionSorter		当数据库抛出一些不可恢复的异常时，抛弃连接
//     * filters		属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有:监控统计用的filter：stat日志用的filter;log4j防御注入的filter:wall
//     * proxyFilters		类型是List<com.alibaba.druid,filter.Filter>，如果同时配置filter和proxyFilters，是组合关系，并非
//     * @return
//     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid(){
//        return  new DruidDataSource();
//    }
//
//
//    /**
//     * 配置 Druid 监控管理后台的Servlet；
//     * 内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
//     *  http://localhost:8080/druid/login.html
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        // 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet 的父类 com.alibaba.druid.support.http.ResourceServlet 中找到
//        Map<String,String> initParams = new HashMap<>();
//
//        initParams.put("loginUsername","admin");
//        initParams.put("loginPassword","123456");
//        initParams.put("allow",""); //默认就是允许所有访问
//
//        //deny：Druid 后台拒绝谁访问，表示禁止此ip访问
//        // initParams.put("deny","192.168.10.132");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//    /**
//     * 配置一个web监控的filter
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean webStatFilter(){
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//
//        Map<String,String> initParams = new HashMap<>();
//        initParams.put("exclusions","*.js,*.css,/druid/*");
//
//        bean.setInitParameters(initParams);
//
//        bean.setUrlPatterns(Arrays.asList("/*"));
//
//        return  bean;
//    }
//
//
//}
