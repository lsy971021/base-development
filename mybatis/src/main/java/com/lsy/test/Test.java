package com.lsy.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test() throws IOException {
        InputStream stream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        //创建工厂对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //一个factory代表一个数据源，从创建开始一直运行
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(stream);
        SqlSession session = factory.openSession();
        List<Object> find = session.selectList("find");
        find.forEach(v -> System.out.println(v));
        session.close();
    }
}
