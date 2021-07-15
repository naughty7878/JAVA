package com.test.spring.webflux;

import com.test.spring.webflux.handler.UserHandler;
import com.test.spring.webflux.service.UserService;
import com.test.spring.webflux.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.*;
import reactor.netty.http.server.HttpServer;


public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");

        // 防止程序结束
        System.in.read();
    }

    // 创建服务器完成适配
    public void createReactorServer() {
        //创建服务器
        HttpServer httpServer = HttpServer.create();

        // 创建路由
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        // 创建handler适配
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        httpServer.handle(adapter).bindNow();
    }

    // 创建Router路由
    public RouterFunction<ServerResponse> routingFunction() {
        //创建hanler对象
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);

        // 如果请求的 HTTP 方法是GET并且给定的pattern与请求路径匹配，则返回一个RequestPredicate匹配。
        RequestPredicate requestPredicate1 = RequestPredicates.GET("/user/{id}")
                // 增加接收类型 json
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        // 如果请求的 HTTP 方法是GET并且给定的pattern与请求路径匹配，则返回一个RequestPredicate匹配。
        RequestPredicate requestPredicate2 = RequestPredicates.GET("/user")
                // 增加接收类型 json
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        //设置路由
        return RouterFunctions
                // 增加路由 (请求断言， 处理器)
                .route( requestPredicate1, handler::getUserById)
                // 增加路由 (请求断言， 处理器)
                .andRoute( requestPredicate2, handler::getUsers);
    }
}