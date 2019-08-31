package com.test.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CustomNameAware implements BeanNameAware, ApplicationContextAware{

	private String name;
	
	public void setBeanName(String name) {
		this.name = name;
		System.out.println("CustomNameAware：" + name);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("CustomNameAware：" + applicationContext.getBean(name).hashCode());
		 
	}

}
