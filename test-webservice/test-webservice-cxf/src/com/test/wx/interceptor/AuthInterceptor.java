package com.test.wx.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

/**
 * 服务端权限拦截器
 * @author H__D
 * @date 2017年8月2日 下午2:22:02
 *
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public AuthInterceptor() {
		super(Phase.PRE_INVOKE); //拦截器在调用方法之前拦截SOAP消息  
	}

	/**
	 * 拦截器操作
	 * 信息如下
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	 * 		<soap:Header>
	 * 			<authHeader>
	 * 				<name>hd</name>
	 * 				<password>123456</password>
	 * 			</authHeader>
	 * 		</soap:Header>
	 * 		<soap:Body>
	 * 			<ns2:sayHello xmlns:ns2="http://ws.test.com/">
	 * 				<arg0>Jack</arg0>
	 * 			</ns2:sayHello>
	 * 		</soap:Body>
	 * </soap:Envelope>
	 */
	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		System.out.println("com to auth interceptor...");
		
		//获取SOAP信息的所有Header
		List<Header> headers = msg.getHeaders();
		
		if(headers == null || headers.size() < 1)
		{
			throw new Fault(new IllegalArgumentException("没有Header，拦截器实施拦截"));
		}
		
		boolean isAuth = false;
		//获取Header携带的用户名和密码信息
		for (Header header : headers) {
			//判断认证信息头
			if(new QName("authHeader").equals(header.getName()))
			{
				//提取认证信息
				Element element = (Element) header.getObject();
				String name = element.getElementsByTagName("name").item(0).getTextContent();
				String password = element.getElementsByTagName("password").item(0).getTextContent();
				
				if(name.equals("hd") && password.equals("123456"))
				{
					isAuth = true;
					break;
				}
			}
		}
		
		if(isAuth)
		{
			System.out.println("认证成功！！！");
		}else
		{
			throw new Fault(new IllegalArgumentException("用户名或密码不正确")); 
		}
	}

}
