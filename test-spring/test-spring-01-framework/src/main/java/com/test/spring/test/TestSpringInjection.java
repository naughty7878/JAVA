package com.test.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.bean.BeanPerson;
import com.test.spring.injection.InjectionService;

public class TestSpringInjection {
		
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-injection.xml");
		InjectionService injectionService = (InjectionService) context.getBean("injectionService");
		injectionService.save("ont record");
	}
	
}
