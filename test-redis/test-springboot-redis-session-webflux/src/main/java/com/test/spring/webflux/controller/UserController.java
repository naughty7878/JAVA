package com.test.spring.webflux.controller;

import com.test.spring.webflux.entity.User;
import com.test.spring.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // id 查询
    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // 查询所有
    @GetMapping("/user")
    public Flux<User> getUsers(ServerWebExchange exchange) {
        exchange.getSession().map(webSession -> {
            String uid = (String) webSession.getAttributes().get("uid");
            System.out.println("uid = " + uid);
            webSession.getAttributes().put("uid", "xxx0001");
            return webSession;
        }).subscribe(System.out::println);
        return userService.getAllUser();
    }

    // 添加
    @PostMapping("/user/add")
    public Mono<Void> addUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.addUser(userMono);
    }
}
