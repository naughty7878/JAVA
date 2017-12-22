package com.test.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 客户端添加用户信息拦截器
 * @author H__D
 * @date 2017年8月2日 下午2:47:08
 *
 */
public class AddUserInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private String name;
	private String password;

	public AddUserInterceptor(String name, String password) {
		
		super(Phase.PRE_PROTOCOL);//准备协议化时拦截
		this.name = name;
		this.password = password;
	}

	/**
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
		//获取消息头
		List<Header> headers = msg.getHeaders();
		
		//创建文档
		Document document = DOMUtils.createDocument();
		//创建根目录
		Element rootEle = document.createElement("authHeader");
		
		//配置head信息的用户名和密码
		Element nameEle = document.createElement("name");
		nameEle.setTextContent(name);
		Element passwordEle = document.createElement("password");
		passwordEle.setTextContent(password);
		
		rootEle.appendChild(nameEle);
		rootEle.appendChild(passwordEle);
		//将信息添加到头
		headers.add(new Header(new QName("authHeader"), rootEle));
	}
	
}
