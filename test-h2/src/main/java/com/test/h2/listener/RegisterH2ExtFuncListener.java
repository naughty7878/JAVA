package com.test.h2.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RegisterH2ExtFuncListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		ServletContext servletContext = servletContextEvent.getServletContext();

		// 1、注册myid函数的SQL语句，语句格式：CREATE ALIAS [IF NOT EXISTS] newFunctionAliasName [DETERMINISTIC] FOR classAndMethodName
		String sql = "CREATE ALIAS IF NOT EXISTS myid  FOR \"com.test.h2.function.H2DBFunctionExt.myid\"";

		// 2、从ServletContext获取 连接对象
		Connection con = (Connection) servletContext.getAttribute("connection");
		try {
			
			// 3、获取Statement对象
			Statement stmt = con.createStatement();

			// 4、执行sql
			stmt.execute(sql);

			System.out.println("H2数据库扩展函数注册成功！");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("H2数据库扩展函数注册失败！");
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		// ServletContextListener.super.contextDestroyed(servletContextEvent);
	}

}
