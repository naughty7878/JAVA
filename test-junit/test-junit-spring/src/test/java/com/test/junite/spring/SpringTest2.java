package com.test.junite.spring;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 利用 @BeforeClass 注解启动Spring容器，开始测试
 * @BeforeClass：它会在所有方法运行前被执行，static修饰
 */
public class SpringTest2 {

    private static ApplicationContext context = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(MyConfig.class);
    }


    @Test
    public void test() {
        Date date = (Date) context.getBean("date");
        System.out.println(date);
    }

    @Configuration
    static class MyConfig {
        @Bean
        public Date date(){
            return new Date();
        }
    }
}
