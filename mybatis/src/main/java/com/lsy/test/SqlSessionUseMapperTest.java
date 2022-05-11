package com.lsy.test;

import com.lsy.mapper.DepartmentMapper;
import com.lsy.pojo.Department;
import com.lsy.util.Page;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 需在sqlMapperConfig中的mappers下开启扫包
 */
public class SqlSessionUseMapperTest {

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
    public void insert() {
        SqlSession session = factory.openSession();
        //获取mapper接口
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        Department department = new Department() {{
            setAge(20);
            setGender(1);
            setName("刘亦菲");
            setTeam("Princess");
        }};
        int isSuccess = mapper.insert(department);
        //对数据库进行修改时需要提交操作
        session.commit();
        session.close();
        System.out.println(isSuccess == 1 ? "插入成功" : "插入失败");
    }

    @org.junit.Test
    public void findAll() {
        SqlSession session = factory.openSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        List<Department> find = mapper.findAll();
        //添加log4j可查看控制台输出的sql语句动态拼接
        find.forEach(v -> System.out.println(v));
        session.close();
    }

    @org.junit.Test
    public void findById() {
        SqlSession session = factory.openSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        Department department = mapper.findById(2);
        System.out.println(department);
        session.close();
    }


    /**
     * 利用自定的的工具类来实现分页
     */
    @Test
    public void findByPage(){
        SqlSession session = factory.openSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        Page page = new Page(){{
            setPageIndex(1);
            setShowdata(1);
        }};
        page.setList(mapper.findByPage(page));
        page.getList().forEach(v -> System.out.println(v));
        session.close();
    }

    /**
     * mybatis分页插件，RowBounds
     */
    @Test
    public void findByRowBounds(){
        SqlSession session = factory.openSession();
        DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
        /**
         * 第一个参数： 从第几页开始(0代表第一页)
         * 第二个参数： 去多少条数据
         */
        RowBounds rb = new RowBounds(0,3);
        List<Department> list = mapper.findByRowBounds(rb);
        list.forEach(v -> System.out.println(v));
        session.close();
    }

}
