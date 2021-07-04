package com.test.q10_producerconsumer;

import java.util.concurrent.CountDownLatch;

public class TestEntrySet {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }
        }, "BBB").start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }

        }, "CCC").start();

        new Thread(() -> {
            try {
                Thread.sleep(3100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }

        }, "DDD").start();

        new Thread(() -> {
            try {
                Thread.sleep(3300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }

        }, "EEE").start();


        new Thread(() -> {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t " + System.currentTimeMillis());
            synchronized (TestEntrySet.class) {
                System.out.println(Thread.currentThread().getName() + "\t 获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "\t 释放锁");
            }

        }, "FFF").start();

        countDownLatch.await();
        System.out.println("程序结束");
    }
}
