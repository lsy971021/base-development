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

/**
 * 懒加载
 */
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
        Subject subject = mapper.findById(1);
        session.close();
        //未开启懒加载： 两个sql都会被执行
        //开启懒加载： 若查询结果的属性都没被调用，则两个sql都不会被执行

        //-----------------------------------------------

        //开启懒加载和积极加载： 调用其中一个表的属性，两个sql都会被执行
        //开启懒加载和消极加载： 调用其中一个表的属性，只有该表的sql会执行
        System.out.println(subject.getDid());
        System.out.println(subject.getDepartment());
    }
}
