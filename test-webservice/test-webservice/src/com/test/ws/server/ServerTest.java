package com.test.ws.server;

import javax.xml.ws.Endpoint;

import com.test.ws.HelloWSImpl;

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
		Endpoint.publish(address, new HelloWSImpl());
		System.out.println("发布webservice成功！");
		
	}
}
