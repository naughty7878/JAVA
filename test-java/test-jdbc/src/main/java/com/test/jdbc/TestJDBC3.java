package com.test.jdbc;

import java.sql.*;

public class TestJDBC3 {

    /**
     * 验证JDBC的事务的隔离级别
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

        // 事务的隔离级别
        int transactionIsolation = con.getTransactionIsolation();
        System.out.println(transactionIsolation);

        // 设置事务的隔离级别
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        try {
            // 开启事物，手动提交
            con.setAutoCommit(false);

            //3: 编写SQL
            String sql = "select count(1) from user_tx";

            int n = 1;
            while (n < 2) {
                //4: 建立一个预处理的声明对象: 解释sql  发送SQL
                PreparedStatement pre = con.prepareStatement(sql);

                //5: 发送SQL  返回一个ResultSet
                ResultSet rs = pre.executeQuery();

                //6:编历结果集
                while (rs.next())//从数据库的取一行数据，是否还有下一行
                {
//                    int id = rs.getInt(1);  //从1开始
//                    String name = rs.getString(2);
//                    System.out.println("id:" + id + "\t名称:" + name);
                    System.out.println(rs.getString(1));
                }
                System.out.println("-------");
                // 手动修改数据库中的数据，查看变化
                Thread.sleep(1000);
            }
            // 手动提交事务
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常时回滚，不一定在catch语句中，
            // 只要在con.commit()前需要回滚时执行都可
            con.rollback();
        } finally {
            //7: 关闭连接
            con.close();
        }

    }
}