package com.test.junite._01base;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit的运行流程
 */
public class JunitFlowTest {

	/**
	 * 1、@BeforeClass修饰的方法会在所有方法被调用前被执行
	 * 而且该方法是静态的，所有当测试类被加载后接着就会运行它
	 * 而且在内存中它只会存在一份实例，它比较适合加载配置文件
	 * 2、@AfterClass所修饰的方法通常用来对资源的清理，如关闭数据库的连接
	 * 3、@Before和@After会在每个测试方法的前后各执行一次
	 * 
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("this is @BeforeClass ...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("this is @AfterClass ...");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("this is @Before ...");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("this is @After ...");
	}

	@Test
	public void test1() {
		System.out.println("this is test1 ...");
	}
	
	@Test
	public void test2() {
		System.out.println("this is test2 ...");
	}

}
