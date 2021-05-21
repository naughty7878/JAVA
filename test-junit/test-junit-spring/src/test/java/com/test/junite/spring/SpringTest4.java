package com.test.junite.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// junit整合spring的测试
@RunWith(SpringRunner.class)
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

