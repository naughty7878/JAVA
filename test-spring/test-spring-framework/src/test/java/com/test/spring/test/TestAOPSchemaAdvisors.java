package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.aop.schema.advice.Fit;
import com.test.spring.aop.schema.advice.biz.AspectBiz;
import com.test.spring.aop.schema.advice.service.InvokeService;
import com.test.spring.beanannotation.BeanAnnotation;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPSchemaAdvisors extends UnitTestBase {

	public TestAOPSchemaAdvisors() {
		super("classpath*:spring-aop-schema-advisors.xml");
	}

	@Test
	public void testBiz() {
		InvokeService invokeService = super.getBean("invokeService");
		invokeService.invoke();
		System.out.println("------");
		invokeService.invokeException();
	}

}
