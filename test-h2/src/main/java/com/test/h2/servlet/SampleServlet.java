package com.test.h2.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 从ServletContext获取 连接对象
		Connection con = (Connection) getServletContext().getAttribute("connection");

		try {
			// 新建数据表
			String ctreateTable = "DROP TABLE test IF EXISTS; create table test(id integer,name VARCHAR(22) )";
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
			// 发送SQL 返回一个ResultSet
			ResultSet rs = prepareStatement.executeQuery();

			// 编历结果集
			while (rs.next())// 从数据库的取一行数据，是否还有下一行
			{
				int id = rs.getInt(1); // 从1开始
				String name = rs.getString(2);
				System.out.println("id:" + id + "\t名称:" + name);
			}
			resp.getWriter().print("SUCCESS");
		} catch (Exception e) {
			resp.getWriter().print("FAILE");
		}
		
	}

}
