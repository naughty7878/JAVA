package com.test.spring.aop.schema.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class CustomAspect {
	
	public void before(){
		System.out.println("CustomAspect before ...");
	}
	
	public void afterReturning(){
		System.out.println("CustomAspect afterReturning ...");
	}
	 
	public void afterThrowing(){
		System.out.println("CustomAspect afterThrowing ...");
	}
	
	public void after(){
		System.out.println("CustomAspect after ...");
	}
 
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		
		Object obj = null;
		System.out.println("CustomAspect around1 ...");
		obj = pjp.proceed();
		System.out.println("CustomAspect around2 ...");
		
		return obj;
	}
	
	public Object aroundInit(ProceedingJoinPoint pjp, String bizName, int times) throws Throwable{
		System.out.println("CustomAspect aroundInit: " + bizName + "	---	" + times);
		Object obj = null;
		System.out.println("CustomAspect around1 ...");
		obj = pjp.proceed();
		System.out.println("CustomAspect around2 ...");
		
		return obj;
	}
	
}
