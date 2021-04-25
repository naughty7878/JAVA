package com.test.junit.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MyConfig {
    @Bean
    public Date date(){
        return new Date();
    }
}
