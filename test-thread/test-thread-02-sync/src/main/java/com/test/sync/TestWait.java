package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class TestWait {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("t1获取锁成功，开始执行，因为t1调用了wait()方法，直接升级为重量级锁");
                    System.out.println("2\n" + ClassLayout.parseInstance(obj).toPrintable());
                    obj.notify();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("t2 获取偏向锁成功开始执行");
                    System.out.println("1\n" + ClassLayout.parseInstance(obj).toPrintable());
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

        //让t1执行完同步代码块中方法。
        TimeUnit.SECONDS.sleep(3);
        t1.start();
    }
}
