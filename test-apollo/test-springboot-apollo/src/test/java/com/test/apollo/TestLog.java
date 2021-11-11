package com.test.apollo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLog {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${sms.enable}")
    private String value;

    @Test
    public void contextLoads() {
        //System.out.println();
        //日志的级别;
        //由低到高 trace<debug<info<warn<error //可以调整输出的日志级别;日志就只会在这个级别以以后的高级别生效 logger.trace("这是trace日志...");
        logger.debug("这是debug日志..."); //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别;root
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }

    @Test
    public void test01() throws InterruptedException {
        System.out.println("value = " + value);

        while (true) {
            System.out.println("value = " + value);
            Thread.sleep(3000L);
        }
    }
}