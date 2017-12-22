package com.test.dom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestDom {

	public static void main(String[] args) {
		
		write();
		
		read();
		
		update();
		
	}



	

	public static void write() {  
		
		//文档构建工厂
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
        	//文档构建器
			DocumentBuilder builder = dbf.newDocumentBuilder();  
			//文档
			Document doc = builder.newDocument();
			//设置xml文件是否独立
			doc.setXmlStandalone(true);
			//设置xml文件版本,默认1.0
			doc.setXmlVersion("1.1");
			
			//创建根目录节点
			Element root = doc.createElement("conpany");
			//设置节点属性
			root.setAttribute("name", "hd");
			//添加根节点
			doc.appendChild(root);
			
			
			Element department = doc.createElement("department");
			department.setAttribute("name", "test");
			//设置节点文本
			department.setTextContent("123456");
			//添加到根节点
			root.appendChild(department);
			
			// 工厂类，用来获取转换对象
			TransformerFactory transFactory = TransformerFactory.newInstance();
			//转化对象
			Transformer transFormer = transFactory.newTransformer();
			// 设置文档自动换行
			transFormer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			//设置编码方式，默认UTF-8
			//transFormer.setOutputProperty(OutputKeys.ENCODING, "GB2312");  
			//文件源
			DOMSource domSource = new DOMSource(doc);
			
			File file = new File("src/doc-write.xml");
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			//输出文件流
			FileOutputStream out = new FileOutputStream(file);
			
			//结果流
			StreamResult xmlResult = new StreamResult(out);
			//转化
			transFormer.transform(domSource, xmlResult);
			
			System.out.println("创建生成文件位置===========" + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void read() {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			//输入流
			InputStream is = TestDom.class.getClassLoader().getResourceAsStream("test.xml");
			//文档构建器解析，得到文档
			Document doc = builder.parse(is);
			
			//获取根目录，元素
			Element root = doc.getDocumentElement();
			if (root == null) 	return;
			
			//获取元素名字
			System.out.print(root.getNodeName());
			//获取元素属性name的值
			System.out.println("\t" + root.getAttribute("name"));

			//获取根元素下的子节点，此方法获取节点（节点包括：标签间的文本，和空白部分）
			NodeList departmentNodes = root.getChildNodes();
			if (departmentNodes == null) 	return;
			
			//遍历节点
			for (int i = 0; i < departmentNodes.getLength(); i++) {

				Node department = departmentNodes.item(i);
				if (department != null && department.getNodeType() == Node.ELEMENT_NODE) {//非空白文本标签
					
					//获取节点名字
					System.out.print("\t" + department.getNodeName());
					//先获取节点属性集，再获取属性name的值
					System.out.println("\t" + department.getAttributes().getNamedItem("name").getNodeValue());
					
					//获取节点下面的所有子节点
					NodeList employees = department.getChildNodes();
					if (employees == null) continue;
					
					for (int j = 0; j < employees.getLength(); j++) {

						Node employee = employees.item(j);
						if (employee != null && employee.getNodeType() == Node.ELEMENT_NODE) {

							System.out.print("\t" + "\t" + employee.getNodeName());
							System.out.print("\t" + employee.getAttributes().getNamedItem("id").getNodeValue());
							System.out.print("\t" + employee.getAttributes().getNamedItem("name").getNodeValue());
							System.out.println("\t" + employee.getTextContent().trim());

						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	public static void update() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream is = TestDom.class.getClassLoader().getResourceAsStream("test.xml");
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			if (root == null) return;
			
			// 修改属性
			root.setAttribute("name", "hd2");
			NodeList departmentNodes = root.getChildNodes();
			if (departmentNodes != null) {
				for (int i = 0; i < departmentNodes.getLength() - 1; i++) {
					
					Node department = departmentNodes.item(i);
					if (department.getNodeType() == Node.ELEMENT_NODE) {
						String departmentName = department.getAttributes().getNamedItem("name").getNodeValue();
						if ("department3".equals(departmentName)) {
							
							// 删除节点
							root.removeChild(department);
						} else if ("department2".equals(departmentName)) {
							
							//新增节点
							Element newChild = doc.createElement("employee");
							newChild.setAttribute("name", "employee4");
							newChild.setTextContent("44444");
							department.appendChild(newChild);
						}
					}
				}
			}

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			transFormer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			DOMSource domSource = new DOMSource(doc);
			
			File file = new File("src/dom-test.xml");
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			transFormer.transform(domSource, xmlResult);
			
			System.out.println("修改生成文件位置===========" + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
            
}
