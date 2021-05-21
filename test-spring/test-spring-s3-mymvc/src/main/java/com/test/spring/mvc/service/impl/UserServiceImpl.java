package com.test.spring.mvc.service.impl;

import com.test.spring.mvc.bean.User;
import com.test.spring.mvc.service.UserService;
import com.test.spring.mymvc.annotation.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Override
    public void findUser() {
        System.out.println("====UserServiceImpl#findUser()====");
    }

    @Override
    public User findData() {
        return new User(1, "小白", "abc");
    }
}
