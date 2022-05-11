package com.lsy.test;

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

public class SqlSessionTest {
    @Test
    public void insert(){
        SqlSession session = factory.openSession();
        Department department = new Department(){{
           setAge(18);
           setGender(1);
           setName("刘亦菲");
           setTeam("Princess");
        }};
        int isSuccess = session.insert("insert", department);
        //对数据库进行修改时需要提交操作
        session.commit();
        session.close();
        System.out.println(isSuccess==1?"插入成功":"插入失败");
    }

    @org.junit.Test
    public void findAll() {
        SqlSession session = factory.openSession();
        List<Department> find = session.selectList("findAll");
        //添加log4j可查看控制台输出的sql语句动态拼接
        find.forEach(v -> System.out.println(v));
        session.close();
    }
    @org.junit.Test
    public void findById(){
        SqlSession session = factory.openSession();
        Department department = session.selectOne("findById",1);
        System.out.println(department);
        session.close();
    }

    private SqlSessionFactory factory;

    /**
     * @Before 每次执行前先执行before
     * @throws IOException
     */
    @Before
    public void before() throws IOException {
        InputStream stream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        //创建工厂对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //一个factory代表一个数据源，从创建开始一直运行
        //参数可指定environment（sqlMapperConfig.xml中配置的id）
        //第三个参数可指定properties属性，此优先级最高
        factory = sqlSessionFactoryBuilder.build(stream,"development");
    }
}
