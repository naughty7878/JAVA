package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.aop.aspectj.biz.CustomBiz;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAspectJ extends UnitTestBase {

	public TestAspectJ() {
		super("classpath*:spring-aop-aspectj.xml");
	}

	@Test
	public void testBiz() {
		CustomBiz customBiz = super.getBean("customBiz");
		customBiz.save("This is Test");
	}

}
