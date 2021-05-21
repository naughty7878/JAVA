package com.test.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCycle3 implements InitializingBean, DisposableBean{


	public void start() {
		System.out.println("Bean start ...");
	}

	public void stop() {
		System.out.println("Bean stop ...");
	}
	
	public void destroy() throws Exception {
		System.out.println("Bean destroy ...");
		
	} 
  
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bean afterPropertiesSet ...");
	}

	public void defaultInit() {
		System.out.println("Bean defaultInit ...");
	}

	public void defaultDestroy() {
		System.out.println("Bean defaultDestroy ...");
	}
}
