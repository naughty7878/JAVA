package com.test.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

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
}
