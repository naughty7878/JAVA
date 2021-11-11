package com.test.apollo;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

/**
 * @author H__D
 * @description TODO
 * @date 2021/11/11
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    // apollo 支持 @Value注解
    // sms.enable = false
    @Value("${sms.enable}")
    private String value;

    // apollo 自己的json注解
    // jsonArrayProperty = [{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
    @ApolloJsonValue("${jsonArrayProperty:[]}")
    private List<Map<String, String>> jsonArrayProperty;

    // apollo 自己的json注解
    // jsonBeanProperty = {"someString":"hello","someInt":100}
    @ApolloJsonValue("${jsonBeanProperty:{}}")
    private Map<String, String> jsonBeanProperty;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("value = " + value);
        System.out.println("jsonArrayProperty = " + jsonArrayProperty);
        System.out.println("jsonBeanProperty = " + jsonBeanProperty);

//        while (true) {
//            System.out.println("value = " + value);
//            Thread.sleep(3000L);
//        }
    }
}

