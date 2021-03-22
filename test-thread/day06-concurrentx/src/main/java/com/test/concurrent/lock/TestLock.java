package com.test.concurrent.lock;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    // 锁
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "：获得锁，进入线程。。。。");
                Scanner scanner = new Scanner(System.in);
//                int n = 0;
//                while ((n = scanner.nextInt()) == 0) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "：释放锁，退出线程。。。。");
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "：获得锁，进入线程。。。。");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "：释放锁，退出线程。。。。");
            }
        };

        t1.setName("t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.setName("t2");
        t2.start();
    }
}
