package com.test.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCycle2 implements InitializingBean, DisposableBean{


	public void destroy() throws Exception {
		System.out.println("Bean destroy ...");
		
	}
  
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bean afterPropertiesSet ...");
	}

}
