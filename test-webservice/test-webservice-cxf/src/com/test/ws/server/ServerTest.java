package com.test.ws.server;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.apache.cxf.message.Message;

import com.test.ws.HelloWSImpl;
import com.test.wx.interceptor.AuthInterceptor;

/**
 * 发布Web Service
 * @author H__D
 * @date 2017年7月28日 上午11:40:48
 *
 */
public class ServerTest {

	public static void main(String[] args) {
		
		//定义WebService的发布地址，这个地址就是提供给外界访问Webervice的URL地址，URL地址格式为：http://ip:端口号/xxxx
		String address = "http://127.0.0.1:8989/test-webservice/hellows";
		//使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
		Endpoint endpoint = Endpoint.publish(address, new HelloWSImpl());
		
		//打印endpoint,可以看到endpoint实际上是一个 org.apache.cxf.jaxws22.EndpointImpl 对象
		System.out.println("endpoint -->" + endpoint);
		
		//强转为EndpointImpl对象
		EndpointImpl endpointImpl = (EndpointImpl) endpoint;
		
		//服务端的日志入拦截器
		List<Interceptor<? extends Message>> inInterceptors = endpointImpl.getInInterceptors();
		inInterceptors.add(new LoggingInInterceptor());
		
		//服务端的自定义拦截器：验证用户名和密码
		inInterceptors.add(new AuthInterceptor());
		
		//服务器端的日志出拦截器
		List<Interceptor<? extends Message>> outInterceptors = endpointImpl.getOutInterceptors();
		outInterceptors.add(new LoggingOutInterceptor());
		
		System.out.println("发布webservice成功！");
		
	}
}
