package com.test.ws.client;


import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;

/**
 * 调用WebService的客户端
 * @author H__D
 * @date 2017年8月3日 上午10:57:00
 *
 */
public class WSClient {

	public static void main(String[] args) {
		//创建一个用于产生WeatherWS实例的工厂，WeatherWS类是wsimport工具生成的
		WeatherWS factory = new WeatherWS();
		//通过工厂生成一个WeatherWSSoap实例，WeatherWSSoap是wsimport工具生成的
		WeatherWSSoap weatherWSSoap = factory.getWeatherWSSoap();
		System.out.println(weatherWSSoap.getClass());
		
		//调用WeatherWSSoap的getWeather方法,获取天气信息
		ArrayOfString weather = weatherWSSoap.getWeather("深圳", null);
		for (String string : weather.getString()) {
			System.out.println(string);
			System.out.println("----------------");
		}
		
	}

}
