package com.test.jooq.spring;

import com.test.jooq.codegen.tables.daos.UserDao;
import com.test.jooq.codegen.tables.pojos.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author h__d
 * @description TODO
 * @date 2021/10/25
 */
@ComponentScan(value = {"com.test.jooq.codegen", "com.test.jooq.spring"})
public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMain.class);

        UserDao bean = context.getBean(UserDao.class);

        List<User> all = bean.findAll();
        System.out.println("all = " + all);
    }

}
