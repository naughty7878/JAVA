package com.test.q10_producerconsumer;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class TestWaitSet {

    volatile Object obj = new Object();

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(8000);
                    System.out.println(Thread.currentThread().getName() + "\t -释放锁");
                    obj.notify();
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + "\t -获得锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
                obj.notify();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "\t -释放锁");
                    obj.notify();
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + "\t -获得锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
                obj.notify();
            }
        }, "BBB").start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "\t -释放锁");
                    obj.notify();
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + "\t -获得锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
                obj.notify();
            }
        }, "CCC").start();

        new Thread(() -> {
            try {
                Thread.sleep(3200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "\t -释放锁");
                    obj.notify();
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + "\t -获得锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
                obj.notify();
            }
        }, "DDD").start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(3300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
//            synchronized (TestWait.class) {
//                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
//                try {
//                    Thread.sleep(3000);
//                    System.out.println(Thread.currentThread().getName() + "\t 释放锁");
//                    wait();
//                    System.out.println(Thread.currentThread().getName() + "\t 获得锁");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                countDownLatch.countDown();
//                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
//            }
//
//        }, "EEE").start();
//
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(3500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
//            synchronized (TestWait.class) {
//                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                countDownLatch.countDown();
//                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
//            }
//
//        }, "FFF").start();

        countDownLatch.await();
        System.out.println("程序结束");
    }
}
