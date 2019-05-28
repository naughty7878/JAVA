package com.test.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.h2.util.Tool;

/**
 * Hello world!
 *
 */
public class TestH2TCPServer {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {

		// start the TCP Server
		Server server = Server.createTcpServer(new String[] { "-tcpAllowOthers", "-tcpPort", "9090" }).start();
		System.out.println("TCP Server 启动了......");
		
		Thread.sleep(30000);
		
		// stop the TCP Server
		server.stop();
		System.out.println("TCP Server 停止了......");

	}
}
