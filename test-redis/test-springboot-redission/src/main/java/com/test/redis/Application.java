package com.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 测试 Redis客户端 Redission 的使用
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
