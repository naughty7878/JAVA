package com.test.mvc.service;

import com.test.mvc.controller.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

//    @Autowired
//    HelloController helloController;

    public Integer testAutowired(){
//        System.out.println("helloController = " + helloController);
        return 1;
    }
}
