package com.lsy.test;

import java.sql.*;

/**
 * JDBC连接数据库
 */
public class JDBCTest {

    public void manipulate() throws Exception {
        //1.注册驱动 :  通知程序 使用什么数据库
        Class.forName("com.mysql.jdbc.Driver");  //反射运行时
        //2.获得连接  连接数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Liusiyuuan1");
        //2.获得预处理对象,将sql语句发送给数据库进行编译
        //? 表示占位符
//        String sql = "INSERT INTO user VALUES(null,?, ? , ?) ";
        String sql = "select * from user where id<10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        /**
         {
         //也可以这样写
         Statement statement = connection.createStatement();
         int i = statement.executeUpdate("INSERT INTO tbl_user VALUES(null, 1, 2 , 3)");
         }
         */
        //3.赋值数据 通过pst对象 .set数据类型(?所在的位置, 真实的数据)
        pst.setString(1, "tom");        //1代表第一个占位符要赋的值
        pst.setString(2, "tom");        //2代表第二个占位符要赋的值
        //4.发送数据到数据库 进行执行
        //executeUpdate()  DML
        //int count = pst.executeUpdate();
        //executeQuery()  DQL
        //4.处理结果集
        ResultSet resultSet = pst.executeQuery();
        ////指针向下移动一位
        boolean flag = resultSet.next();
        //获得返回值
        int count = resultSet.getInt(1); //1代表第一列，也可以赋一个要获取的字段名
        // 获取指针行的name字段的值
        String name = resultSet.getString("name");
        System.out.println(name);
        //5.释放资源
        resultSet.close();
        connection.close();

    }
}
