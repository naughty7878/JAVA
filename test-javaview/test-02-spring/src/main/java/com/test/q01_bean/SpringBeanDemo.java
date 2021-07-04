package com.test.q01_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanDemo {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        BeanPerson person1 = context.getBean(BeanPerson.class);
        BeanPerson person2 = context.getBean(BeanPerson.class);
        person1.say(" world");
        System.out.println(person1 == person2);
    }

}
