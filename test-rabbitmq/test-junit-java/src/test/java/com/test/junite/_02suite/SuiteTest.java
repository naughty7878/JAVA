package com.test.junite._02suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件
 * 运行所有的测试类
 * @author h__d
 *
 */
// 运行器
@RunWith(Suite.class)
// 套件类
@Suite.SuiteClasses({TaskTest1.class, TaskTest2.class, TaskTest3.class})
public class SuiteTest {
	/**
	 * 测试套件就是组织测试类一起运行的
	 *
	 * 步骤
	 * 1、写一个作为测试套件的入口类，这个类不包含其他的方法
	 * 2、更改测试运行器Suite.class
	 * 3、将要测试的类作为数组传到Suite.SuiteClasses({})
	 */
}
