package com.test.spring.aop.schema.advice.biz;

public class AspectBiz {

	public void biz() {
		System.out.println("AspectBiz biz ....");
		// throw new RuntimeException("runtime exception ...");
	}

	public void init(String bizName, int times) {
		System.out.println("AspectBiz init: " + bizName + "	---	" + times);
	} 
}
