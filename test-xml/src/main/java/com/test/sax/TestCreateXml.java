package com.test.sax;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class TestCreateXml {

	public static void main(String[] args) {
		//创建SAXTransformerFactory实例
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			//创建TransformerHandler实例
			TransformerHandler handler = factory.newTransformerHandler();
			//创建Transformer实例
			Transformer transformer = handler.getTransformer();
			//设置输出的xml属性，encoding为编码，
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			//indent是确保输出的xml文件能够自动换行
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			//创建Result对象，将Result对象加载到TransHandler中
			// 注意：1、这一步必须在Transformer.setOutputProperty()之后，不然设置的xml属性将不生效
			// 2、这一步也必须在TransformerHandler.startDocument()之前，不然会报错。
			// 分析源码后发现，startDocument()会先判断result是否为空，为空则报错
			Result result = new StreamResult("src/test-sax-create.xml");
			handler.setResult(result);

			// 创建属性Attribute对象
			AttributesImpl attr = new AttributesImpl();
			//开始写文件
			handler.startDocument();
			//写入根节点conpany
			handler.startElement("", "", "conpany", attr);

			//清空属性，
			attr.clear();
			//设置属性
			attr.addAttribute( "", "", "name", "", "1");
			//写入根节点的子节点book
			handler.startElement("", "", "department", attr);
			
			attr.clear();
			//分别写入book节点的子节点
			attr.addAttribute( "", "", "name", "", "employee1");
			attr.addAttribute( "", "", "id", "", "1");
			handler.startElement("", "", "employee", attr);
			//写入子节点内容
			handler.characters("123".toCharArray(), 0, "123".toCharArray().length);
			//写入子节点末尾
			handler.endElement("", "", "employee");
			//写入department节点末尾
			handler.endElement("", "", "department");
			//写入根节点末尾
			handler.endElement("", "", "conpany");
			//写文件结束
			handler.endDocument();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}