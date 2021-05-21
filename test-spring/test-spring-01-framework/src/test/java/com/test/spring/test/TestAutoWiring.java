package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.autowiring.AutoWiringService;
import com.test.spring.interfaces.OneInterface;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAutoWiring extends UnitTestBase{
	
	public TestAutoWiring() {
		super("classpath*:spring-autowiring.xml");
	}
	
	@Test
	public void testAutoWiring() {
		
		String word = "Tom";
		AutoWiringService autoWiringService = super.getBean("autoWiringService");
		autoWiringService.say(word);
	}
	 
	

}
