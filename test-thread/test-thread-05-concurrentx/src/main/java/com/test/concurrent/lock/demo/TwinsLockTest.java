package com.test.concurrent.lock.demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        //启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        //每隔1秒换行
        for (int i = 0; i < 100; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }


    public static class SleepUtils{
        public static final void second(long sec) {
            try {
                TimeUnit.SECONDS.sleep(sec);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
