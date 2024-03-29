package com.test.junite._01base;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class AnnotationTest {

	/**
	 * @Test:将一个普通的方法修饰成为一个测试方法
	 * 	@Test(expected=XX.class)
	 * 	@Test(timeout=毫秒)
	 * @BeforeClass：它会在所有方法运行前被执行，static修饰
	 * @AfterClass：它会在所有方法运行后被执行，static修饰
	 * @Before：会在每一个测试方法运行前被执行一次
	 * @After：会在每一个测试方法运行后被执行一次
	 * @Ignore：所修饰的测试方法会测试运行器被忽略
	 * @RunWiht：可以更改测试运行器 org.junit.runner.Runner
	 */
	@Test(expected=ArithmeticException.class)
	public void testDivide() {
		assertEquals(3, 3 / 0);
	}
	
	@Test(timeout=2000)
	public void testTimeout() {
		while (true) {
			System.out.println("run forever ...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Ignore
	@Test
	public void testIgnore() {
		System.out.println("this is testIgnore ...");
	}
}











