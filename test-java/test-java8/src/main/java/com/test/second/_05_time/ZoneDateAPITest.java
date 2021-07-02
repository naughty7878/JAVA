package com.test.second._05_time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateAPITest {
    @Test
    public void test1() {
        ZoneId.getAvailableZoneIds().stream().sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("now = " + now);
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("zonedDateTime = " + zonedDateTime);

    }
}
