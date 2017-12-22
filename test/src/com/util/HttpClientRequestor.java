package com.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 需用用到三个jar包
 * 1.commons-codec-1.9.jar
 * 2.commons-httpclient-3.1.jar
 * 3.commons-logging-1.1.3.jar
 * @author H__D
 *
 */
public class HttpClientRequestor {

	/**
	 * 
	 * @param requestType 请求类型
	 * @param urlStr 请求地址
	 * @param body 请求发送内容
	 * @return 返回内容
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String requestMethod(String requestType,String urlStr,String body) throws HttpException, IOException {

	  HttpClient client = new HttpClient(); 
      // 设置代理服务器地址和端口      
      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
         HttpMethod method=new GetMethod("http://java.sun.com");
      //使用POST方法
      //HttpMethod method = new PostMethod("http://java.sun.com");
      client.executeMethod(method);

      //打印服务器返回的状态
      System.out.println(method.getStatusLine());
      //打印返回的信息
      System.out.println(method.getResponseBodyAsString());
      //释放连接
      method.releaseConnection();
	
		return null;
	}
	
	
	
	public static void main(String[] args) throws HttpException, IOException {
		requestMethod(null, null, null);
	}
}
