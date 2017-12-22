package com.test.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class TestParseXml {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		//创建SAXTransformerFactory实例
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//创建TransformerHandler实例
		SAXParser saxParser = saxParserFactory.newSAXParser();
		
		MyHandler myHandler = new MyHandler();
		
		saxParser.parse(TestParseXml.class.getClassLoader().getResourceAsStream("test.xml"), myHandler);
	}
	
}


class MyHandler extends DefaultHandler{
	
	/**
	 * 开始解析文档时，调用的方法
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("---------startDocument-------");
	}
	
	/**
	 * 文档解析完结时，调用的方法
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("---------endDocument-------");
		super.endDocument();
	}
	
	/**
	 * 开始解析每个元素时，调用的方法，会重复调用
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//qName 元素标签
		System.out.println("---------startElement-------" + qName);
		
		// 处理属性
		if(attributes != null )
		{
			for (int i = 0; i < attributes.getLength(); ++i)
	        {
	            String attrName = attributes.getQName(i);
	            String attrValue = attributes.getValue(i);
	            System.out.print("\t "+ attrName +  "=" + attrValue);
	        }
		}
		System.out.println("");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		System.out.println("---------endElement-------" + qName);
	}
	
	/**
	 * 解析到每个元素的内容时会调用此方法
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch,start,length);
		System.out.println("---------characters-------" + "content == "+ content);
	}
}
