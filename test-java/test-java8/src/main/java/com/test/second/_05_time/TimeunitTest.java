package com.test.second._05_time;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimeunitTest {
    
    @Test
    public void test1() {
        TimeUnit seconds = TimeUnit.SECONDS;
        long l = seconds.toSeconds(1);
        System.out.println("l = " + l);
    }
}
