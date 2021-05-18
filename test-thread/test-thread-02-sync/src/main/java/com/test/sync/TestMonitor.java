package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 重量级锁测试
 */
public class TestMonitor {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("t1 获取偏向锁成功");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        TimeUnit.SECONDS.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1：t2 获取锁失败导致锁升级,此时t1还在执行");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("t2 获取偏向锁失败，最终升级为重量级锁，等待thread1执行完毕，获取重量锁成功");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        //对象头打印需要时间,先让thread1获取偏向锁
        TimeUnit.SECONDS.sleep(3);
        t2.start();

        //确保t1和t2执行结束
        t1.join();
        t2.join();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("t1和t2执行结束");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        Thread t3 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("t3 再次获取");
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        });
        t3.start();
    }
}
