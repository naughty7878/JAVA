package com.test.q0_cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 */
public class SpinLockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t invoke myLock");
        while (!threadAtomicReference.compareAndSet(null, thread)) {

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t invoke myUnLock");
        while (!threadAtomicReference.compareAndSet(thread, null)) {

        }
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            System.out.println(Thread.currentThread().getName() + "\t 获取锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }).start();

        new Thread(() -> {
            spinLockDemo.myLock();
            System.out.println(Thread.currentThread().getName() + "\t 获取锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }).start();
    }
}
