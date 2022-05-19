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
 * mybatis缓存
 */
public class MybatisCachingTest {
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

    /**
     * 一级缓存
     * mybatis默认开启一级缓存
     * 同一个session内执行同一个sql语句，只有第一次是从数据库中拿的，之后都是从本地缓存中获取
     */
    @Test
    public void firstCache(){
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department1 = mapper.findById(1);
        System.out.println(department1);
        System.out.println("==========执行同一sql，走缓存==========");
        //若提交commit则会清除缓存(不在一个session内),若要使用一级缓存不能commit
        sqlSession.commit();
        Department department2 = mapper.findById(1);
        System.out.println(department2);
        System.out.println("==========执行不同一sql，不走缓存==========");
        Department department3 = mapper.findById(3);
        System.out.println(department3);
        //close()会清除缓存
        sqlSession.close();
    }

    /**
     * 二级缓存
     *
     * 开启二级缓存：
     *      在pom添加两个依赖   <artifactId>mybatis-ehcache</artifactId>  <artifactId>ehcache</artifactId>
     *      sqlMapperConfig.xml中开启二级缓存  <setting name="cacheEnabled" value="true"/>
     *      mapper映射文件中开启二级缓存 <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
     *          所有查询都会使用二级缓存，若某个sql语句不希望使用缓存数据，可在属性中添加 useCache="false"
     *      实体类实现序列化 implements Serializable
     *
     * 在mapper的同一namespace中,如果其他insert,update,delete操作数据后需要刷新缓存，如果不执行刷新缓存会出现脏读，默认情况下
     * flushCache="true"会刷新缓存，改为false则不会
     *
     * mapper范围级别的，同一个作用于内多个session之间是可以共享缓存数据的
     */
    @Test
    public void secondCache(){
        SqlSession sqlSession1 = factory.openSession();
        SqlSession sqlSession2 = factory.openSession();
        DepartmentMapper mapper1 = sqlSession1.getMapper(DepartmentMapper.class);
        DepartmentMapper mapper2 = sqlSession2.getMapper(DepartmentMapper.class);
        Department department1 = mapper1.findById(1);
        System.out.println(department1);
        sqlSession1.close();
        Department department2 = mapper2.findById(1);
        System.out.println(department2);
        sqlSession2.close();

        System.out.println("=============不使用二级缓存=============");

        SqlSession sqlSession3 = factory.openSession();
        SqlSession sqlSession4 = factory.openSession();
        DepartmentMapper mapper3 = sqlSession3.getMapper(DepartmentMapper.class);
        DepartmentMapper mapper4 = sqlSession4.getMapper(DepartmentMapper.class);
        List<Department> all1 = mapper3.findAll();
        System.out.println(all1);
        sqlSession3.close();
        List<Department> all2 = mapper4.findAll();
        System.out.println(all2);
        sqlSession3.close();

        System.out.println("=============更新数据后不刷新缓存/刷新缓存(可修改mapper中insert的属性)=============");

        SqlSession sqlSession5 = factory.openSession();
        SqlSession sqlSession6 = factory.openSession();
        DepartmentMapper mapper5 = sqlSession5.getMapper(DepartmentMapper.class);
        DepartmentMapper mapper6 = sqlSession6.getMapper(DepartmentMapper.class);
        List<Department> all3 = mapper5.findAll();
        System.out.println(all3);
        int isInsert = mapper5.insert(new Department() {{
            setAge(18);
            setTeam("King");
            setName("刘西美子");
            setGender(1);
        }});
        sqlSession5.commit();
        System.out.println(isInsert == 1 ? "插入成功" : "插入失败");
        sqlSession5.close();
        List<Department> all4 = mapper6.findAll();
        System.out.println(all4);
        sqlSession6.close();
    }
}
