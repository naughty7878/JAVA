package com.test.junite.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 利用 @RunWith 运行器，来运行一个Spring环境，@ContextConfiguration 导入相关配置
 *
 * @RunWith作用
 * @RunWith 就是一个运行器
 * @RunWith(JUnit4.class) 就是指用JUnit4来运行
 * @RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境
 * @RunWith(Suite.class) 的话就是一套测试集合，
 */
// junit整合spring的测试
// SpringRunner 继承了SpringJUnit4ClassRunner，
// 没有扩展任何功能；使用SpringRunner，名字简短而已
@RunWith(SpringRunner.class)
// @RunWith(SpringJUnit4ClassRunner.class)
// 加载核心xml配置文件，自动构建spring容器
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringTest3 {

    @Autowired
    private ApplicationContext contex;


    @Test
    public void test() {
        Date date = (Date) contex.getBean("date");
        System.out.println(date);
    }
}
