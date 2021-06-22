package com.test.concurrent.locksupport;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockSupport2 {

    public static void main(String[] args) {



        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，开始执行!");
                for(;;){//spin 自旋
                    System.out.println(Thread.currentThread().getName() + "，准备park住当前线程");
                    LockSupport.park();
//                    System.out.println("Thread.interrupted() == " + Thread.interrupted());
                    System.out.println(Thread.currentThread().getName() + "，当前线程已经被唤醒");
                }
            }
        },"t0");

        t0.start();

        try {
            Thread.sleep(5000);
            System.out.println("准备唤醒" + t0.getName() + "线程!");
//            LockSupport.unpark(t0);
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Test
    public void test() {

        Thread t1 = new Thread(() -> {
            //                lock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AAAA开始了");
//                condition.await();
            LockSupport.park();
            LockSupport.park();
            System.out.println("AAAA结束了");
//                lock.unlock();
        }, "AAAAAA");
        t1.start();



        new Thread(()->{
            System.out.println("BBBBBB开始了");
//            condition.signal();
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println("BBBBBB结束了");
        }, "BBBBBB").start();



        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
