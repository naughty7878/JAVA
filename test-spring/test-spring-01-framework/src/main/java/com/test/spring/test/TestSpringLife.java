package com.test.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.bean.BeanLifeCycle3;


public class TestSpringLife {
		
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-life.xml");
		BeanLifeCycle3 beanLifeCycle = (BeanLifeCycle3) context.getBean("beanLifeCycle3");
		context.close();
	}
	
}
