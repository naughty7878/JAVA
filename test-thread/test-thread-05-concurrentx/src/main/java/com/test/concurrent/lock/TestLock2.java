package com.test.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestLock2 {

    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static volatile int flag = -1;

    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            int finalI = i;
            Thread t = new Thread(() -> {
                ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
                try {
                    writeLock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("读异常锁。。。。。");
                }
                System.out.println(Thread.currentThread().getName() + " get lock");
                System.out.println("flag = " + flag);
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (flag == finalI) {
                        break;
                    }
                }
                writeLock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock");
            }, "t-" + i);
            list.add(t);

            t.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        ;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            flag = scanner.nextInt();
            if(flag > 10) {
                list.get(flag % 10).interrupt();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
