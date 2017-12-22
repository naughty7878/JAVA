package com.test.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestDom4j {

	public static void main(String[] args) {
		createXml();
		parserXml();
	}

	// Dom4j创建Xml文档
	public static void createXml() {  
		// 创建一个文档对象  
        Document document = DocumentHelper.createDocument(); 
        // 在根节点添加元素  
        Element conpany = document.addElement("conpany"); 
        // 在conpany下添加子节点  
        Element department = conpany.addElement("department"); 
        // 在department下添加子节点  
        Element employee = department.addElement("employee"); 
        // 给employee节点添加内容  
        employee.setText("123"); 
        // 给employee节点添加内容  
        employee.addAttribute("name", "employee1");
        try {  
            Writer fileWriter = new FileWriter("src/test-dom4j-create.xml"); 
            
            //输出格式
            OutputFormat xmlFormat = new OutputFormat();
            //设置编码
            xmlFormat.setEncoding("UTF-8");
            // 设置换行 
            xmlFormat.setNewlines(true);
            //自动缩进
            xmlFormat.setIndent(true);
            // 使用4个空格进行缩进, 可以兼容文本编辑器 
            xmlFormat.setIndent("    "); 
            
            //輸出
            XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);  
            xmlWriter.write(document);  
            xmlWriter.close();  
        } catch (IOException e) {  
            System.out.println(e.getMessage());
        }

	}
	
	
    // Dom4j解释Xml文档  
   public static void parserXml() {  
	   //文件
       File inputXml = new File("src/test-dom4j-create.xml");  
       //解析器
       SAXReader saxReader = new SAXReader();  
       try {  
    	   //得到文档
           Document document = saxReader.read(inputXml);  
           //根目录
           Element conpany = document.getRootElement();  
           for (Iterator i = conpany.elementIterator(); i.hasNext();) {  
               Element department = (Element) i.next();  
               for (Iterator j = department.elementIterator(); j.hasNext();) { // 遍例节点  
                   Element employee = (Element) j.next();  
                   //获取元素的文本以及属性
                   System.out.println(employee.getName() + "\t" + employee.getText() + "\t" + employee.attributeValue("name"));  
               }  
 
           }  
       } catch (DocumentException e) {  
           System.out.println(e.getMessage());  
       }  
   }   
}
