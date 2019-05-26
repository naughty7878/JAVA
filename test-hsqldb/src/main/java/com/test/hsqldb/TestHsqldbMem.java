package com.test.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHsqldbMem {

	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		// 加载HSQL DB的JDBC驱动
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
        
        // 链接内存库，自动创建内存数据库，得到联接对象  connection
        String url="jdbc:hsqldb:men:db";
        Connection con=DriverManager.getConnection(url, "sa", "");
        
        // 新建数据表
        String ctreateTable="create table test(id integer,name VARCHAR(22) )";
        Statement createStatement = con.createStatement();
        long f1 = createStatement.executeUpdate(ctreateTable);
        System.out.println("创建表：" + f1);
        
        // 插入数据
        String insertSql = "INSERT INTO test VALUES(1,'小明')";
        Statement insertStatement = con.createStatement();
        long f2 = insertStatement.executeUpdate(insertSql);
        System.out.println("插入数据：" + f2);
        
        // 查询数据
        String selectSql = "select id,name from test";
        PreparedStatement prepareStatement = con.prepareStatement(selectSql);
        // 发送SQL  返回一个ResultSet
        ResultSet rs=prepareStatement.executeQuery();

        // 编历结果集
        while(rs.next())//从数据库的取一行数据，是否还有下一行
        {
            int id=rs.getInt(1);  //从1开始
            String name=rs.getString(2);
            System.out.println("id:"+id+"\t名称:"+name);
        }
        
        // 关闭连接
        con.close();
		
	}

}
