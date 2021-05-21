package com.test.spring.aop.api;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class CustomThrowsAdvice implements ThrowsAdvice {
	
	public void afterThrowing(Exception ex) throws Throwable {
		System.out.println("CustomThrowsAdvice afterThrowing 1");
	} 

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		System.out.println("CustomThrowsAdvice afterThrowing 2 : " + method.getName() + " --- " + target.getClass().getName());
	} 
}
