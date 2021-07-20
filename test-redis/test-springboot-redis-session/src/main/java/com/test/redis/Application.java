package com.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @EnableRedisHttpSession 可以加也可以不加，
// RedisHttpSessionConfiguration会被自动导入
@SpringBootApplication
public class Application {

    /**
     * spring-session-data-redis Session共享
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
