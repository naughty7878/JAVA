package com.test.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("~~~条件等待前~~~");
            condition.await();
            System.out.println("~~~条件等待后~~~");
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("~~~条件唤醒前~~~");
            condition.signal();
            System.out.println("~~~条件唤醒后~~~");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestCondition testCondition = new TestCondition();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    testCondition.conditionWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    testCondition.conditionSignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1.isAlive() = " + t1.isAlive());
        t2.start();
    }
}
