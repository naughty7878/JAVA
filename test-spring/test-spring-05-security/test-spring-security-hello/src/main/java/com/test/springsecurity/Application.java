package com.test.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Spring Security
 * 启动默认用户：user
 * 密码在控制台
 * Using generated security password: 93de40ee-54bc-45a6-9dda-bfcfd22dcc27
 *
 * Spring Security本质是一个过滤器链
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(
        securedEnabled=true,
        prePostEnabled = true)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
