package com.test.jdbc;

import java.sql.*;

public class TestJDBC {

    /**
     * JDBC --java database connection (JAVA 数据据连接技术)它是一个接口
     * 步骤：
     * 1: 加载驱动
     * 2: 得到联接对象  connection
     * 3: 编写SQL
     * 4: 建立一个预处理的声明对象: 解释sql  发送SQL
     * 5: 发送SQL  返回一个ResultSet
     * .executeQuery(); 发送一个查询的SQL语句，  返回一个ResultSet
     * .executeUpdate();发送一个非查询的SQL语句，  返回一个 int n （代表是数据库受影响的行数）
     * .execute();发送一个SQL语句，  返回一个 boolean (是否有ResultSet)
     * 6: 编历结果集
     * 7: 关闭连接
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

        //3: 编写SQL
        String sql = "select id,name from user";

        //4: 建立一个预处理的声明对象: 解释sql  发送SQL
        PreparedStatement pre = con.prepareStatement(sql);

        //5: 发送SQL  返回一个ResultSet
        ResultSet rs = pre.executeQuery();

        //6:编历结果集
        while (rs.next())//从数据库的取一行数据，是否还有下一行
        {
            int id = rs.getInt(1);  //从1开始
            String name = rs.getString(2);
            System.out.println("id:" + id + "\t名称:" + name);
        }

        //7: 关闭连接
        con.close();
    }
}