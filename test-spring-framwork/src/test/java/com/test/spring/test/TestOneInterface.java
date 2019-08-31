package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.interfaces.OneInterface;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestOneInterface extends UnitTestBase{
	
	public TestOneInterface() {
		super("classpath*:spring-ioc.xml");
	}
	
	@Test
	public void testIoc() {
		
		OneInterface ontInterface = getBean("ontInterface");
		ontInterface.say("Tom");
		
	}
	 
	

}
