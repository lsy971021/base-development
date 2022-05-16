package com.lsy.test;

import com.lsy.mapper.DepartmentMapper;
import com.lsy.pojo.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis分布式缓存
 * 为了提高系统并发和性能，一般对系统进行分布式部署(集群部署)，此时二级缓存只是存在一台服务器上，不能多个服务器共享
 *
 * 使用ehcache,redis,memcache缓存框架
 * 在原二级缓存设置的基础上参考注释 {@link MybatisCachingTest#secondCache()}
 * 多了一个配置文件，ehcache.xml
 *
 */
public class MybatisCachingPlusTest {
    private SqlSessionFactory factory;

    @Before
    public void before() throws IOException {
        InputStream stream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        //创建工厂对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //一个factory代表一个数据源，从创建开始一直运行
        //参数可指定environment（sqlMapperConfig.xml中配置的id）
        //第三个参数可指定properties属性，此优先级最高
        factory = sqlSessionFactoryBuilder.build(stream, "development");
    }


    @Test
    public void Cache(){
        SqlSession sqlSession = factory.openSession();
        SqlSession sqlSession1 = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        DepartmentMapper mapper1 = sqlSession1.getMapper(DepartmentMapper.class);
        Department department1 = mapper.findById(3);
        System.out.println(department1);
        sqlSession.close();
        Department department2 = mapper1.findById(3);
        System.out.println(department2);
        sqlSession.close();
    }
}
