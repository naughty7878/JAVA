package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.bean.BeanLifeCycle;
import com.test.spring.bean.BeanLifeCycle2;
import com.test.spring.bean.BeanLifeCycle3;
import com.test.spring.bean.BeanLifeCycle4;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanLifteCycle extends UnitTestBase {

	
	public TestBeanLifteCycle() {
		super("classpath*:spring-life.xml");
	}

	@Test
	public void testBeanLife() {
		BeanLifeCycle beanLifeCycle = getBean("beanLifeCycle");
	}

	@Test
	public void testBeanLife2() {
		BeanLifeCycle2 beanLifeCycle2 = getBean("beanLifeCycle2");
	}
	
	@Test
	public void testBeanLife3() {
		BeanLifeCycle3 beanLifeCycle3 = getBean("beanLifeCycle3");
	}
	
	@Test
	public void testBeanLife4() {
		BeanLifeCycle4 beanLifeCycle4 = getBean("beanLifeCycle4");
	}
}
