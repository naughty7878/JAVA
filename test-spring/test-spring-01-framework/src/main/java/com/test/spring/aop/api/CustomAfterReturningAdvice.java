package com.test.spring.aop.api;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CustomAfterReturningAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("CustomAfterReturningAdvice afterReturning : " + method.getName() + " --- "
				+ target.getClass().getName() + " --- " + returnValue);
	}

}
