package com.test.spring.resource;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class CustomResource implements ApplicationContextAware{

	private ApplicationContext applicationContext; 
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}

	public void resource(String resourceStr) throws IOException {
		Resource resource = applicationContext.getResource(resourceStr);
		System.out.println("CustomResource：" + resource.getFilename());
		System.out.println("CustomResource：" + resource.contentLength());
	} 
}
