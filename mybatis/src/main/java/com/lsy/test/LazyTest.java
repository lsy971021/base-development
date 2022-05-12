package com.lsy.test;

import com.lsy.mapper.SubjectMapper;
import com.lsy.pojo.Subject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

public class LazyTest {

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

    @org.junit.Test
    public void findById() {
        SqlSession session = factory.openSession();
        SubjectMapper mapper = session.getMapper(SubjectMapper.class);
        Subject department = mapper.findById(2);
        session.close();
        System.out.println(department.getDid());
    }
}
