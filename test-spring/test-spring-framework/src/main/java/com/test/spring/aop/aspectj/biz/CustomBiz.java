package com.test.spring.aop.aspectj.biz;

import org.springframework.stereotype.Service;

import com.test.spring.aop.aspectj.CustomMethod;

@Service
public class CustomBiz {

	@CustomMethod("CustomBiz save CustomMethod.")
	public String save(String arg) {
		System.out.println("CustomBiz save: " + arg);
//		throw new RuntimeException("运行异常：Save fail");
		 return "Save Success!";
	}
	
}
