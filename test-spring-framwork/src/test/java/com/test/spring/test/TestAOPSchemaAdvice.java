package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.aop.schema.advice.Fit;
import com.test.spring.aop.schema.advice.biz.AspectBiz;
import com.test.spring.beanannotation.BeanAnnotation;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPSchemaAdvice extends UnitTestBase{
	
	public TestAOPSchemaAdvice() {
		super("classpath*:spring-aop-schema-advice.xml");
	}

	@Test
	public void testBiz() {
		AspectBiz aspectBiz = super.getBean("aspectBiz");
		aspectBiz.biz(); 
	}
	
	@Test
	public void testInit() {
		AspectBiz aspectBiz = super.getBean("aspectBiz");
		aspectBiz.init("TestAOPSchemaAdvice", 3); 
	}
	
	@Test
	public void testFit() {
		Fit fit = super.getBean("aspectBiz");
		fit.filter(); 
	}
}
