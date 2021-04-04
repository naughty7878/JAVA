package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.interfaces.OneInterface;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAware extends UnitTestBase{
	
	public TestAware() {
		super("classpath*:spring-aware.xml");
	}
	
	@Test
	public void testCustomApplicationContext() {
		
		System.out.println("CustomApplicationAware：" + super.getBean("customApplicationContext"));
		
	}
	
	
	@Test
	public void testCustomNameAware() {
		
		System.out.println("=========");
		System.out.println("CustomNameAware==：" + super.getBean("customNameAware").hashCode());
		
	}
	

}
