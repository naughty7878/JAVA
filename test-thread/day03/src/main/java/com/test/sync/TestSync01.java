package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

public class TestSync01 {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);

        // 打印对象布局
        System.out.println(ClassLayout.parseInstance(0).toPrintable());

        // 加锁
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(0).toPrintable());
        }
    }
}
