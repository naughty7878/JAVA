package com.test.second._05_time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    @Test
    public void test1() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
        String s = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("s = " + s);
    }


}
