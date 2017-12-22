package com.test.ws.client;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

/**
 * 调用WebService的客户端
 * @author H__D
 * @date 2017年8月3日 上午11:13:39
 *
 */
public class WSClient {

	public static void main(String[] args) {
		//创建一个用于产生MobileCodeWS实例的工厂，MobileCodeWS类是wsimport工具生成的
		MobileCodeWS factory = new MobileCodeWS();
		//通过工厂生成一个MobileCodeWSSoap实例，MobileCodeWSSoap是wsimport工具生成的
		MobileCodeWSSoap mobileCodeWSSoap = factory.getMobileCodeWSSoap();
		System.out.println(mobileCodeWSSoap.getClass());
		
		//调用MobileCodeWSSoap的getMobileCodeInfo方法,获取天气信息
		String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo("151****1111", null);
		System.out.println(mobileCodeInfo);
		
	}
}
