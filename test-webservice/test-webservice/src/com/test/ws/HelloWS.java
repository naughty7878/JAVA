package com.test.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 定义SEI(WebService EndPoint Interface)终端
 * @author H__D
 * @date 2017年7月28日 上午11:35:34
 *
 */
//使用@WebService注解标注WebServiceI接口
@WebService
public interface HelloWS {
	
	//使用@WebMethod注解标注WebServiceI接口中的方法
	@WebMethod
	public String sayHello(String name);
	
}
