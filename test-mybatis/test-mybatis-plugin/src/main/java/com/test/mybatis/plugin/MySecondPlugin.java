package com.test.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts(
		{
			@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
		})
public class MySecondPlugin implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("MySecondPlugin...intercept:"+invocation.getMethod());
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		System.out.println("MySecondPlugin...plugin:"+target);
		return Plugin.wrap(target, this);
	}
  
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
