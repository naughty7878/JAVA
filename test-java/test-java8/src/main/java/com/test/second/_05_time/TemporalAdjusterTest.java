package com.test.second._05_time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterTest {
    // TemporalAdjuster 时间调整器
    @Test
    public void test6() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("dateTime = " + dateTime);

        LocalDateTime dateTime1 = dateTime.withDayOfMonth(10);
        System.out.println("dateTime1 = " + dateTime1);

        // 使用时间调整器
        LocalDateTime dateTime2 = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("dateTime2 = " + dateTime2);

        // 自定义时间调整器
        LocalDateTime dateTime3 = dateTime.with(time -> ((LocalDateTime) time).plusDays(8));
        System.out.println("dateTime3 = " + dateTime3);
    }
}
