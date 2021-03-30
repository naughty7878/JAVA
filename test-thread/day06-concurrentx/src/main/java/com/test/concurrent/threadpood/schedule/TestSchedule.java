package com.test.concurrent.threadpood.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestSchedule {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        pool.submit(() -> {
            System.out.println("hello world");
        });

        pool.shutdown();
    }
}
