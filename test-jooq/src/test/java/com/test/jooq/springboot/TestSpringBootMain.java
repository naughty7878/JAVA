package com.test.jooq.springboot;

/**
 * @author h__d
 * @description TODO
 * @date 2021/10/25
 */

import com.test.jooq.codegen.tables.daos.UserDao;
import com.test.jooq.codegen.tables.pojos.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBootMain {


    @Autowired
    ApplicationContext context;

    @Autowired
    UserDao userDao;

    @Test
    public void contextLoads() {
        System.out.println("context = " + context);

        List<User> all = userDao.findAll();
        System.out.println("all = " + all);
    }

}
