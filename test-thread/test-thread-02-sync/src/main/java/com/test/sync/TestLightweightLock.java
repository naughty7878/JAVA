package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 轻量级锁测试
 */
public class TestLightweightLock {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("thread1 获取偏向锁成功");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("thread2 获取偏向锁失败，升级为轻量级锁，获取轻量级锁成功");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        };

        t1.start();
        //让t1死亡
        t1.join();
        System.out.println("thread1执行结束");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        t2.start();
        //t2死亡
        t2.join();
        System.out.println("thread2执行结束，释放轻量级锁");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


        synchronized (obj) {
            System.out.println("=====主线程中，获取锁=====");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
