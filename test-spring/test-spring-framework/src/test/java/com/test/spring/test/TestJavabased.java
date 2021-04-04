package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.autowiring.AutoWiringService;
import com.test.spring.beanannotation.javabased.MyDriverManager;
import com.test.spring.beanannotation.javabased.Store;
import com.test.spring.interfaces.OneInterface;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestJavabased extends UnitTestBase{
	
	public TestJavabased() {
		super("classpath*:spring-beanannotation.xml");
	}
	
	@Test
	public void test() {
		
		Store store = super.getBean("store");
		System.out.println(store.getClass().getName());
	}
 
	@Test
	public void testMyDriverManager() {
		
		MyDriverManager manager = super.getBean("myDriverManager");
		System.out.println(manager.getClass().getName());
	}
 
}
