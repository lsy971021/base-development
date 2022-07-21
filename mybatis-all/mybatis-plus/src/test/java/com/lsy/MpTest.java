package com.lsy;

import com.MybatisPlusApplication;
import com.alibaba.druid.pool.DruidDataSource;
import com.lsy.model.User;
import com.lsy.service.MybatisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisPlusApplication.class})
public class MpTest {

    @Autowired
    private MybatisService mybatisService;

    @Test
    public void update(){
        User user = new User();
        user.setId(1L);
//        user.setEmail(null);
        user.setName("null");
        user.setAge(1);
        mybatisService.updateAge(user);
    }

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        //关闭连接
        connection.close();
    }
}
