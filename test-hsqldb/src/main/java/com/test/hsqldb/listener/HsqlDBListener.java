package com.test.hsqldb.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HsqlDB监听启动类
 * @author H__D
 * @date 2019-05-26 22:38:26
 *
 */
public class HsqlDBListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(HsqlDBListener.class);
	
	private Server server = null;
	
	/**
	 * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
	 * 并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		// 以服务模式运行hsqldb
		// 新建一个hsqldb服务对象
		server = new Server();
		// 设置属性
//		HsqlProperties p = new HsqlProperties(null);
//		server.setProperties(p);
		// 设置数据库名
        server.setDatabaseName(0, "testdb");
        // 设置数据库目录
        server.setDatabasePath(0, sce.getServletContext().getRealPath("/") + "testdb/"+"testdir");
        // 设置端口
        server.setPort(9001);
        //可以使用自定义编写器
        server.setLogWriter(null); 
        //可以使用自定义编写器
        server.setErrWriter(null);  
         // 启动
        server.start();
        
		log.info("Server Hsqldb Start ...");
	}

	/**
	 * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// 关闭
		server.shutdownCatalogs(1);
		
		log.info("Server Hsqldb Stop ...");
	}
	
	
}
