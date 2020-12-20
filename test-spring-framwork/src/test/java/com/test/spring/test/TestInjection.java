package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.injection.InjectionService;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {
	
	public TestInjection() {
		super("classpath*:spring-injection.xml");
	}

	@Test
	public void testInjectionMethod() {
		 
		InjectionService injectionService = getBean("injectionService");
		injectionService.save("ont record");
		 
	}

}
