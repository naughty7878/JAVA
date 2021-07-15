package com.test.spring.webflux;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient是从Spring WebFlux 5.0版本开始提供的一个非阻塞的基于响应式编程的进行Http请求的客户端工具
 * 参考：https://www.cnblogs.com/grasp/p/12179906.html
 */
public class Client {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create();
        String url = "https://www.baidu.com";
        url = "http://localhost:9527/payment/get/1";
        Mono<String> mono = webClient.get().uri(url).retrieve().bodyToMono(String.class);
        // block 阻塞到信号返回，即得到结果
        System.out.println(mono.block());
    }
}
