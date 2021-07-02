package com.test.second._05_time;

import org.junit.Test;

import java.time.LocalTime;

public class LocalTimeTest {
    
    @Test
    public void test1() {
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);
        
        LocalTime time = LocalTime.of(12, 30, 59);
        System.out.println("time = " + time);
        System.out.println("time.getHour() = " + time.getHour());
        System.out.println("time.getMinute() = " + time.getMinute());
        System.out.println("time.getSecond() = " + time.getSecond());
    }
}
