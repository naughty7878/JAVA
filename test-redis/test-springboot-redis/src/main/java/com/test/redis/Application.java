package com.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 测试 RedisTemplate 与 StringRedisTemplate 的使用
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
