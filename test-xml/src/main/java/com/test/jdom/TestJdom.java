package com.test.jdom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class TestJdom  {  
	
	public static void main(String[] args) {
		
		createXml();
		parserXml();
	}
  
    public static void createXml() {  
    	//文档
        Document document = new Document(); 
        //根元素
        Element root = new Element("conpany"); 
        //设置属性
        root.setAttribute("name","root");
        //添加根目录
        document.setRootElement(root);
        
        Element department = new Element("department");  
        department.setAttribute("name","department1");
        //添加到根目录
        root.addContent(department); 
        
        Element employee = new Element("employee");  
        employee.setAttribute("name","employee1");
        employee.setAttribute("id","1");
        //设置文本
        employee.setText("123");
        department.addContent(employee);
        XMLOutputter xmlOut = new XMLOutputter();  
        xmlOut.setFormat(Format.getPrettyFormat());
        try {  
        	xmlOut.output(document, new FileOutputStream("src/test-jdom-create.xml"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void parserXml() {  
    	// 创建一个SAXBuilder
        SAXBuilder builder = new  SAXBuilder();  
        try {  
        	// 通过输入源SAX构造一个Document
            Document document = builder.build("src/test-jdom-create.xml");  
            //获取根节点
            Element root = document.getRootElement();  
            // 获得root节点下面的所有子节点
            List<Element> departmentList = root.getChildren();  
            for (Element  department: departmentList) {
            	// 获得节点属性
				System.out.println(department.getName()+"\t"+department.getAttributeValue("name"));
				List<Element> employeeList = department.getChildren();
				for (Element employee : employeeList) {
					// 获得节点属性和文本
					System.out.println("\t" +  employee.getName() + "\t"+ employee.getAttributeValue("name") + "\t"+ employee.getText());
				}
			} 
        } catch (JDOMException  e) {  
            System.out.println(e.getMessage());  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
}  