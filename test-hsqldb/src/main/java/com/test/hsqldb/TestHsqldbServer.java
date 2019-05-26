package com.test.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.taglibs.standard.resources.Resources;
import org.hsqldb.Database;
import org.hsqldb.DatabaseType;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.persist.ScriptRunner;

public class TestHsqldbServer {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		// 加载HSQL DB的JDBC驱动
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
        
        // 链接内存库，自动创建内存数据库，得到联接对象  connection
        String url="jdbc:hsqldb:hsql://localhost:9001/testdb";
        Connection con=DriverManager.getConnection(url, "sa", "");
        
//        // DatabaseType type, String path, String canonicalPath, HsqlProperties props
//        new Database(null, url, url, null);
//        
//        
//        // 创建ScriptRunner，用于执行SQL脚本
//        ScriptRunner runner = new ScriptRunner(con);
//        runner.setErrorLogWriter(null);
//        runner.setLogWriter(null);
//        // 执行SQL脚本
//        runner.runScript(Resources.getResourceAsReader("hsqldb-init.sql");
        
        // 关闭连接
        con.close();
		
	}

}
