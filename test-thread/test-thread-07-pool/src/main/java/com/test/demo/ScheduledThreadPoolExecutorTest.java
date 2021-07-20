package com.test.demo;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    @Test
    public void test01() throws InterruptedException {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);

        System.out.println(Thread.currentThread().getName()  + "\t---\t" + LocalDateTime.now());

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "\t---\t" + LocalDateTime.now());
        };
        // 延迟3s执行任务
        // command：执行任务
        // delay：初始化延时 3s
        // unit：计时单位
        poolExecutor.schedule(runnable, 3, TimeUnit.SECONDS);

        Thread.sleep(100000);
    }

    @Test
    public void test02() throws InterruptedException {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);

        System.out.println(Thread.currentThread().getName()  + "\t---\t" + LocalDateTime.now());

        Runnable runnable = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t---\t" + LocalDateTime.now());

        };
        // 周期执行任务 5s执行一次
        // command：执行任务
        // initialDelay：初始化延时 2s
        // period：两次开始执行最小间隔时间(前一次执行开始，到后一次执行开始最小间隔时间) 5s
        // unit：计时单位
        poolExecutor.scheduleAtFixedRate(runnable, 2, 5, TimeUnit.SECONDS);

        Thread.sleep(100000);
    }


    @Test
    public void test03() throws InterruptedException {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);

        System.out.println(Thread.currentThread().getName()  + "\t---\t" + LocalDateTime.now());

        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t---\t" + LocalDateTime.now());

        };
        // 周期执行任务 间隔5s执行一次
        // command：执行任务
        // initialDelay：初始化延时 2s
        // delay：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间） 5s
        // unit：计时单位
        poolExecutor.scheduleWithFixedDelay(runnable, 2, 5, TimeUnit.SECONDS);

        Thread.sleep(100000);
    }
}
