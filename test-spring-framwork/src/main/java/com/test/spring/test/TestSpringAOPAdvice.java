package com.test.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.spring.aop.schema.advice.biz.AspectBiz;


public class TestSpringAOPAdvice {
		
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-schema-advice.xml");
		AspectBiz aspectBiz = (AspectBiz) context.getBean("aspectBiz");
		aspectBiz.biz();
		
	}
	
}
