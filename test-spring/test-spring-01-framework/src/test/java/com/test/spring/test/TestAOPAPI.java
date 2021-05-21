package com.test.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.aop.api.BizLogic;
import com.test.spring.aop.api.BizLogicImpl;
import com.test.spring.aop.schema.advice.Fit;
import com.test.spring.aop.schema.advice.biz.AspectBiz;
import com.test.spring.aop.schema.advice.service.InvokeService;
import com.test.spring.beanannotation.BeanAnnotation;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPAPI extends UnitTestBase {

	public TestAOPAPI() {
		super("classpath*:spring-aop-api.xml");
	}

	@Test
	public void testBiz() {
		BizLogic logic = super.getBean("bizLogicImpl");
		logic.save();
	}

}
