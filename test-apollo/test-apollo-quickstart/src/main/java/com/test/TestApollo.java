package com.test;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.time.LocalDateTime;

/**
 * @author H__D
 * @description TODO
 * @date 2021/11/11
 */
public class TestApollo {

    // 方法一 启动JVM参数：-Dapp.id=apollo-quickstart -Denv=DEV -Dapollo.cluster=default -Dapollo.meta=http://127.0.0.1:8080
    // 方法二 System.setProperty("apollo.meta", "http://config-service-url");
    public static void main(String[] args) throws InterruptedException {


        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = "sms.enable";
        String someDefaultValue = "------";
        String value = config.getProperty(someKey, someDefaultValue);
        System.out.println("value = " + value);

        while (true) {
            value = config.getProperty(someKey, someDefaultValue);
            System.out.printf("now: %s, sms.enable: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }
}
