package com.test.spring.test;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.spring.resource.CustomResource;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestCustomResource extends UnitTestBase {

	
	public TestCustomResource() {
		super("classpath*:spring-resource.xml");
	}
	

	@Test
	public void testResource(){
		 
		// String resourceStr = "classpath:config.xml";
		// String resourceStr ="file:/Users/h__d/Documents/workspace-eclipse/test-spring/src/main/resources/config.xml";
		// String resourceStr = "http://tomcat.naughty7878.top/zeus/login/toLogin";
		String resourceStr = "config.xml"; // 默认即为classpath
		
		CustomResource customResource = super.getBean("customResource");
	
		try {
			customResource.resource(resourceStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
