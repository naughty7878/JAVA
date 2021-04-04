package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.beanannotation.BeanAnnotation;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAnnotation extends UnitTestBase{
	
	public TestBeanAnnotation() {
		super("classpath*:spring-beanannotation.xml");
	}

	@Test
	public void testSay() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.say("This is Test");
	}
	
	@Test
	public void testHashCode() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.myHashCode();
		BeanAnnotation bean2 = super.getBean("beanAnnotation");
		bean2.myHashCode();
	} 
}
