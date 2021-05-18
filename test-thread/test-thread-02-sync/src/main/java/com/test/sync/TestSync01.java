package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

public class TestSync01 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        System.out.println(obj);

        // 打印对象布局
        // 001 无锁状态
        System.out.println("第1次：打印");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        // 休眠6s
        Thread.sleep(6000);
        // 001 无锁状态
        System.out.println("第2次：休眠6s");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        // 加锁
        synchronized (obj) {
            System.out.println("第3次：加synchronized之后");
            // 00 轻量级锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        // 调用hashcode()
        System.out.println("第4次：调用hashcode()");
        // 001 无锁状态
        System.out.println(obj.hashCode());
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


        // 加锁
        synchronized (obj) {
            System.out.println("第5次：加synchronized之后");
            // 00 轻量级锁状态
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

    }
}
