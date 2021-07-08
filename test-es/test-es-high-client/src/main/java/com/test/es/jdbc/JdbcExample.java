package com.test.es.jdbc;

import org.elasticsearch.xpack.sql.jdbc.EsDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * JDBC客户端，需要付费才能使用
 * 否则连接 es时报错 current license is non-compliant for [jdbc]
 */
public class JdbcExample {
    public static void main(String[] args) throws Exception {
        // 1. 加载ES驱动
        Class.forName(EsDriver.class.getName());

        // 2. 建立连接
        Properties properties = new Properties();
        properties.put("user", "elastic");
        properties.put("password", "123456");
        Connection connection = DriverManager.getConnection("jdbc:es://http://192.168.0.4:9200/", properties);

        // 3. 准备SQL语句
        String sql = "select id, name from employee";

        // 4. 使用PreparedStatement执行SQL
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. 遍历结果
        while(resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            System.out.println("id：" + id + " name：" + name);
        }

        // 6. 关闭连接
        resultSet.close();
        connection.close();
    }
}