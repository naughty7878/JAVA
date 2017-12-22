package com.test.ws.client;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;

import com.test.interceptor.AddUserInterceptor;
import com.test.ws.HelloWS;
import com.test.ws.HelloWSImplService;

/**
 * 调用WebService的客户端
 * @author H__D
 * @date 2017年7月28日 下午2:39:24
 *
 */
public class WSClient {

	public static void main(String[] args) {
		//创建一个用于产生WebServiceImpl实例的工厂，WebServiceImplService类是wsimport工具生成的
		HelloWSImplService factory = new HelloWSImplService();
		//通过工厂生成一个WebServiceImpl实例，WebServiceImpl是wsimport工具生成的
		HelloWS helloWS = factory.getHelloWSImplPort();
		System.out.println(helloWS.getClass());
	
		//发送请求的客户端对象
		Client client = ClientProxy.getClient(helloWS);
		
		//客户端的日志入拦截器
		List<Interceptor<? extends Message>> inInterceptors = client.getInInterceptors();
		inInterceptors.add(new LoggingInInterceptor());
		
		//客户端的日志出拦截器
		List<Interceptor<? extends Message>> outInterceptors = client.getOutInterceptors();
		outInterceptors.add(new LoggingOutInterceptor());
		
		//添加自定义输出拦截器
		outInterceptors.add(new AddUserInterceptor("hd", "123456"));
		
		//调用WebService的sayHello方法
		String result = helloWS.sayHello("Jack");
		System.out.println(result);
	}

}
