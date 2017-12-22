package com.test.ws;

import javax.jws.WebService;

/**
 * SEI的具体实现
 * @author H__D
 * @date 2017年7月28日 上午11:37:43
 *
 */
//使用@WebService注解标注
@WebService
public class HelloWSImpl implements HelloWS{

	@Override
	public String sayHello(String name) {
		System.out.println("WebService sayHello : " + name);
		return "Hello : " + name;
	}
}
