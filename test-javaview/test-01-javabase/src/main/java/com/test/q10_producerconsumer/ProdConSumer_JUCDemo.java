package com.test.q10_producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConSumer_JUCDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class ShareData {
        private volatile    int number = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void increment() throws InterruptedException {
            lock.lock();
            try {
                // 判断
                while (number != 0) {
                    // 等待
                    condition.await();
                }
                // 增加
                number++;
                System.out.println(Thread.currentThread().getName() + "\t" + number);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void decrement() throws InterruptedException {
            lock.lock();
            try {
                // 判断
                while (number == 0) {
                    // 等待
                    condition.await();
                }
                // 增加
                number--;
                System.out.println(Thread.currentThread().getName() + "\t" + number);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}
