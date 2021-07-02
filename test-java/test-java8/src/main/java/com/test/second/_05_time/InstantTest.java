package com.test.second._05_time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class InstantTest {

    // Instant：时间戳（以Unix元年：1970年1月1日 00:00:00.000 到某个时间之间的毫秒值）
    @Test
    public void test3() {
        Instant now = Instant.now();
        System.out.println("now = " + now);
        System.out.println("now.getEpochSecond() = " + now.getEpochSecond());
        System.out.println("now.toEpochMilli() = " + now.toEpochMilli());

        LocalDateTime time = LocalDateTime.of(1970, 1, 1, 0,0);
        System.out.println("time = " + time);
        Instant instant = time.toInstant(ZoneOffset.UTC);
        System.out.println("instant = " + instant);
        System.out.println("instant.toEpochMilli() = " + instant.toEpochMilli());
        System.out.println("instant.getEpochSecond() = " + instant.getEpochSecond());

        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println("instant1 = " + instant1);
    }

    // Duration：计算两个“时间”之间的间隔
    // Period：计算两个“日期”之间的间隔
    @Test
    public void test4() throws InterruptedException {
        Instant ins1 = Instant.now();
        System.out.println("ins1 = " + ins1);
        Thread.sleep(1234);
        Instant ins2 = Instant.now();
        System.out.println("ins2 = " + ins2);
        Duration between = Duration.between(ins1, ins2);
        System.out.println("between = " + between);
        System.out.println("between.toMillis() = " + between.toMillis());
        System.out.println("between.toNanos() = " + between.toNanos());



        LocalDateTime localDateTime1 = LocalDateTime.now();
        Thread.sleep(1234);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        Duration between1 = Duration.between(localDateTime1, localDateTime2);
        System.out.println("between1 = " + between1);
        System.out.println("between1.toMillis() = " + between1.toMillis());

    }


}
