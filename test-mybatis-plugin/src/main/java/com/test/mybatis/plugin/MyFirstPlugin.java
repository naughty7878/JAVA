package com.test.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * 完成插件签名：
 *		告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 *	@Intercepts(org.apache.ibatis.plugin.Intercepts)和
 *	签名注解@Signature(org.apache.ibatis.plugin.Signature)，这两个注解用来配置拦截器要拦截的接口的方法。
 *
 *	@Intercepts注解中的属性是一个@Signature（签名）数组，可以在同一个拦截器中同时拦截不同的接口和方法。
 *
 *  @Signature中
 *  	type：设置拦截的接口，可选值是4个：Executor、ParameterHandler、ResultSetHandler、StatementHandler
 *  	method：设置拦截接口中的方法名，需要和接口匹配
 *  	args：设置拦截方法的参数类型数组，通过方法名和参数类型可以确定唯一一个方法
 */
@Intercepts(
		{
			@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
		})
public class MyFirstPlugin implements Interceptor {

	/**
	 * intercept：拦截： 拦截目标对象的目标方法的执行；
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("MyFirstPlugin...intercept:" + invocation.getMethod());
		// 动态的改变一下sql运行的参数：以前1号员工，实际从数据库查询3号员工
		Object target = invocation.getTarget();
		System.out.println("当前拦截到的对象：" + target);
		// 拿到：StatementHandler==>ParameterHandler===>parameterObject
		// 拿到target的元数据
		MetaObject metaObject = SystemMetaObject.forObject(target);
		Object value = metaObject.getValue("parameterHandler.parameterObject");
		System.out.println("sql语句用的参数是：" + value);
		// 修改完sql语句要用的参数
		metaObject.setValue("parameterHandler.parameterObject", 3);
		// 执行目标方法
		Object proceed = invocation.proceed();
		// 返回执行后的返回值
		return proceed;
	}

	/**
	 * plugin： 包装目标对象的：包装：为目标对象创建一个代理对象
	 */
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		// 我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
		System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象" + target);
		Object wrap = Plugin.wrap(target, this);
		// 返回为当前target创建的动态代理
		return wrap;
	}

	/**
	 * setProperties：
	 * 		将插件注册时 的property属性设置进来
	 */
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("插件配置的信息：" + properties);
	}

}
