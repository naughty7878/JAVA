package com.test.spring.aop.api;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class CustomBeforeAdivce implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("CustomBeforeAdivce before : " + method.getName() + " --- " + target.getClass().getName());
	}

}
