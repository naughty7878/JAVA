package com.test.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.autowiring.AutoWiringService;
import com.test.spring.bean.BeanPerson;
import com.test.spring.injection.InjectionService;

public class TestSpringAutowiring {
		
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-autowiring.xml");
		String word = "Tom";
		AutoWiringService autoWiringService = (AutoWiringService) context.getBean("autoWiringService");
		autoWiringService.say(word);
	}
	
}
