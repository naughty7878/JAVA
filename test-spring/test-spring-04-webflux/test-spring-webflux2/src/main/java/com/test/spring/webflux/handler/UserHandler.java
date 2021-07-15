package com.test.spring.webflux.handler;

import com.test.spring.webflux.entity.User;
import com.test.spring.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserHandler {
    @Autowired
    private UserService userService;

    public UserHandler() {
    }

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    // id 查询
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        //获取id值
        int userId = Integer.valueOf(request.pathVariable("id"));
        //空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        //调用service方法得到数据
        Mono<User> userMono = userService.getUserById(userId);
        //把userMono进行转换返回
        //使用Reactor操作符flatMap
        Mono<ServerResponse> serverResponseMono = userMono.flatMap(person -> {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters
                            .fromObject(person));
        }).switchIfEmpty(notFound);

        return serverResponseMono;
    }

    // 查询所有
    public Mono<ServerResponse> getUsers(ServerRequest request) {
        //调用service得到结果
        Flux<User> users = userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users,User.class);
    }

    // 添加
    @PostMapping("/user/add")
    public Mono<ServerResponse> addUser(ServerRequest request) {
        //得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.addUser(userMono));
    }


}
