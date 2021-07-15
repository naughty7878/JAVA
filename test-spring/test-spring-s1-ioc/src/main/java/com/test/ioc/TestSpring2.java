package com.test.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestSpring2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        List dateList = context.getBean("dateList", List.class);
        System.out.println("dateList = " + dateList);
    }
}

@Configuration
class MyConfig {

    @Bean
    public Date date1() {
        return new Date();
    }
    @Bean
    public Date date2() {
        return new Date();
    }
    @Bean
    public Date date3() {
        return new Date();
    }

    @Bean
    public List<Date> dateList(List<Date> list) {
        if (list != null) {
            for (Date date : list) {
                System.out.println("date = " + date + "\t" + date.hashCode());
            }
            return list;
        }
        return new ArrayList<>();
    }
}
