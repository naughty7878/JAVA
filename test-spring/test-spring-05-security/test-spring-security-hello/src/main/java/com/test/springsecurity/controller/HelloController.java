package com.test.springsecurity.controller;

import com.test.springsecurity.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security!";

    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/index2")
    public String index2() {
        return "hello index2";
    }


    @GetMapping("/update")
//    @Secured({"ROLE_test", "ROLE_dev"})
//    @PreAuthorize("hasAnyAuthority('test')")
    @PostAuthorize("hasAnyAuthority('test')")
    public String update() {
        System.out.println("update ------------");
        return "hello update";
    }

    @GetMapping("/getAll")
    @PostAuthorize("hasAnyAuthority('test')")
    @PostFilter("filterObject.name == 'admin1'")
    public List<User> getAllUser(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(11,"admin1","6666"));
        list.add(new User(21,"admin2","888"));
        System.out.println(list);
        return list;
    }
}
