package com.test.second._05_time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DurationAndPeriodTest {



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

    @Test
    public void test5() throws InterruptedException {
        LocalDate localDate1 = LocalDate.now();
        Thread.sleep(1234);
        LocalDate localDate2 = LocalDate.of(2022,7,30);

        Period between = Period.between(localDate1, localDate2);
        System.out.println("between = " + between);
        System.out.println("between.toMillis() = " + between.getDays());
        System.out.println("between.getMonths() = " + between.getMonths());
        System.out.println("between.getYears() = " + between.getYears());
    }
}
