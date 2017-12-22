package com.test.ws.client;

import com.test.ws.HelloWSImpl;
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
		HelloWSImpl helloWS = factory.getHelloWSImplPort();
		System.out.println(helloWS.getClass());
		
		//调用WebService的sayHello方法
		String result = helloWS.sayHello("Jack");
		System.out.println(result);
	}

}
