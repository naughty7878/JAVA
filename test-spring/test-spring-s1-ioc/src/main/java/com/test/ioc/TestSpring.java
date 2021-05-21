package com.test.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        BeanPerson person = context.getBean(BeanPerson.class);
        person.say(" world ");
        context.close();
    }

}

