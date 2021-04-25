package com.test.junite.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @RunWith作用
 * @RunWith 就是一个运行器
 * @RunWith(JUnit4.class) 就是指用JUnit4来运行
 * @RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境
 * @RunWith(Suite.class) 的话就是一套测试集合，
 */
// junit整合spring的测试
@RunWith(SpringJUnit4ClassRunner.class)
// 导入配置类，自动构建spring容器
@Import(SpringTest4.MyConfig.class)
public class SpringTest4 {

    @Autowired
    private ApplicationContext contex;

    @Test
    public void test() {
        Date date = (Date) contex.getBean("date");
        System.out.println(date);
    }

    @Configuration
    static class MyConfig{
        @Bean
        public Date date() {
            return new Date();
        }
    }
}

