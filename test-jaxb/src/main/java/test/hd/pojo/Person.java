package test.hd.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import test.hd.util.CDataAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//控制JAXB 绑定类中属性和字段的排序  
@XmlType(propOrder = {   
     "age",   
     "name" 
})  
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Person {

	@XmlElement(name="nn")
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String name;
	
	//@XmlElement(name="aa")
	@XmlAttribute(name="bb")
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}
