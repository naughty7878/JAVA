package com.test.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {

	@Pointcut("execution(* com.test.spring.aop.aspectj.biz.*Biz.*(..))")
	public void pointcut(){};
	
	@Pointcut("within(com.test.spring.aop.aspectj.biz.*)")
	public void bizPointcut(){};
	
	// @Before("execution(* com.test.spring.aop.aspectj.biz.*Biz.*(..))")
	@Before("pointcut()")
	public void before() {
		System.out.println("CustomAspect before ...");
	}
	
	@Before("pointcut() && args(arg)")
	public void beforeWithParam(String arg) {
		System.out.println("CustomAspect beforeWithParam: " + arg);
	}
	
	@Before("pointcut() && @annotation(customMethod)")
	public void beforeWithAnnotation(CustomMethod customMethod) {
		System.out.println("CustomAspect beforeWithAnnotation: " + customMethod.value());
	}
	
	@AfterReturning(pointcut="bizPointcut()", returning="returnValue")
	public void afterReturning(Object returnValue){
		System.out.println("CustomAspect afterReturning: " + returnValue);
	}
	
	@AfterThrowing(pointcut="pointcut()", throwing = "e")
	public void afterThrowing(RuntimeException e){
		System.out.println("CustomAspect afterThrowing: " + e.getMessage());
	}
	
	@After("pointcut()")
	public void after(){
		System.out.println("CustomAspect after ...");
	}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("CustomAspect around1 ...");
		Object obj = pjp.proceed();
		System.out.println("CustomAspect around2 ...");
		System.out.println("CustomAspect around: " + obj);
		return obj;
	}
}
