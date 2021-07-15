package com.test.spring.webflux.service.impl;

import com.test.spring.webflux.entity.User;
import com.test.spring.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    // 创建map集合存储数据
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("小白", 18,"男"));
        this.users.put(2, new User("小黑", 22,"男"));
        this.users.put(3, new User("小黄", 20,"女"));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(1));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> addUser(Mono<User> user) {
        Mono<Void> voidMono = user.doOnNext(person -> {
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
        return voidMono;
    }

}
