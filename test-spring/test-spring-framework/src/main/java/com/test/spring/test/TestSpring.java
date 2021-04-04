package com.test.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.bean.BeanPerson;

public class TestSpring {
		
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		BeanPerson person = context.getBean(BeanPerson.class);
		person.say(" world ");
	}
	
}
