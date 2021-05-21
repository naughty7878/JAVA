package com.test.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CustomApplicationContext implements ApplicationContextAware{

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("CustomApplicationAware：" + applicationContext.getBean("customApplicationContext"));		
	}

}
 