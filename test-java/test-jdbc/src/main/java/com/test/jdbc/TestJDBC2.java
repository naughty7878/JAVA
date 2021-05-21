package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBC2 {

    /**
     * 验证JDBC的事务
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1: 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2: 得到联接对象  connection
        String url = "jdbc:mysql://47.106.74.177:13306/test_mybatis?allowPublicKeyRetrieval=true&useSSL=true";
        Connection con = DriverManager.getConnection(url, "hd", "hd123456");

        try {
            // 开启事物
            // 开启手动事务的关键是con.setAutoCommit(false)，JDBC事务默认是开启的，并且是自动提交：
            // 关闭自动提交：java.sql.Connection.setAutoCommit(false)
            // setAutoCommit(true)：每次操作都会被认为是一个事务并且自动提交
            con.setAutoCommit(false);

            //3: 编写SQL
            String sql = "update user_tx set name = '王五' where id = 3";

            //4: 建立一个预处理的声明对象: 解释sql  发送SQL
            PreparedStatement pre = con.prepareStatement(sql);

            //5: 发送SQL  返回一个ResultSet
            boolean result = pre.execute(sql);

            int n = 1 / 0;

            // 手动提交事务
            con.commit();
        } catch (Exception e) {
            // 出现异常时回滚，不一定在catch语句中，
            // 只要在con.commit()前需要回滚时执行都可
            con.rollback();
        } finally {
            //7: 关闭连接
            con.close();
        }

    }
}