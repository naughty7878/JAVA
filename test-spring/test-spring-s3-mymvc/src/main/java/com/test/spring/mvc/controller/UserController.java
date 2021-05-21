package com.test.spring.mvc.controller;

import com.test.spring.mvc.bean.User;
import com.test.spring.mvc.service.UserService;
import com.test.spring.mymvc.annotation.*;

@Controller
public class UserController {

    @Autowired(value = "userService")
    UserService userService;

    // 定义方法
    @RequestMapping("/findUser")
    public String findUser() {
        userService.findUser();
        return "success.jsp";
    }

    @RequestMapping("/findData")
    @ResponseBody
    public User findData(@RequestParam String id) {
        System.out.println("id = " + id);
        return userService.findData();
    }
}
