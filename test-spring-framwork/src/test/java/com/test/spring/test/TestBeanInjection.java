package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.beanannotation.injection.InjectionService;
import com.test.spring.beanannotation.multibean.BeanInvoker;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanInjection extends UnitTestBase {
	
	public TestBeanInjection() {
		super("classpath*:spring-beanannotation.xml");
	}

	@Test
	public void testInjectionMethod() {
		
		InjectionService injectionService = getBean("injectionServiceImpl");
		injectionService.save("ont record");
		 
	}
	
	@Test
	public void testMultiBean() {
		BeanInvoker invoker = super.getBean("beanInvoker");
		invoker.say();
	}

}
