package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.bean.BeanScope;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanScope extends UnitTestBase {

	
	public TestBeanScope() {
		super("classpath*:spring-scope.xml");
	}

	@Test
	public void testBeanScope() {
		BeanScope beanScope = getBean("beanScope");
		beanScope.say();
		
		BeanScope beanScope2 = getBean("beanScope");
		beanScope2.say();
	}

}
