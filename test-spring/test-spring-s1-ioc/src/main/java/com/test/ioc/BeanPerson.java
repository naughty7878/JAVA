package com.test.ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanPerson implements InitializingBean, DisposableBean {

	public BeanPerson() {
		System.out.println("BeanPerson() ");
	}

	public void say(String word) {
		System.out.println("Hello, " + word);
	}

	@PostConstruct
	public void postConstruct(){
		System.out.println("postConstruct()....");
	}

	public void initMethod(){
		System.out.println("initMethod()....");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()......");
	}

	public void destroyMethod(){
		System.out.println("destroyMethod()....");
	}

	@PreDestroy
	public void preDestroy(){
		System.out.println("preDestroy().....");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()......");
	}
}
