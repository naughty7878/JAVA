package com.test.spring.mymvc.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;

public class XmlPaser {
    // Dom4j解释Xml文档
    public static String parserXml(String xml) {
        //解析器
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = null;
        try {
            inputStream = XmlPaser.class.getClassLoader().getResourceAsStream(xml);
            //得到文档
            Document document = saxReader.read(inputStream);
            //根目录
            Element rootElement = document.getRootElement();
            Element componentScan = rootElement.element("component-scan");

            String basePackage = componentScan.attributeValue("base-package");
            return basePackage;
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        // classpath:springmvc.xml
        String s = XmlPaser.parserXml( "mymvc.xml");
        System.out.println(s);
    }
}
