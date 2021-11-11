package com.test.jooq.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.test.jooq.codegen", "com.test.jooq.springboot"},
        excludeFilters =
        {
                @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.test.spring.*")
        })
public class SpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class);
    }
}