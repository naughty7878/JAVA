package com.test.junite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 测试套件
 * 运行所有的测试类
 * @author h__d
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TaskTest1.class, TaskTest2.class, TaskTest3.class})
public class SuiteTest {
	/**
	 * 1、测试套件就是组织测试类一起运行的
	 * 
	 * 写一个作为测试套件的入口类，这个类不包含其他的方法
	 * 更改测试运行器Suite.class
	 * 将要测试的类作为数组传到Suite.SuiteClasses({})
	 */
}