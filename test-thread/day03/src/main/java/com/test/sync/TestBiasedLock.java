package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 偏向锁测试
 */
public class TestBiasedLock {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object object = new Object();

        System.out.println("同步块前\n" + ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println("同步块中\n" + ClassLayout.parseInstance(object).toPrintable());
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("出同步块\n" + ClassLayout.parseInstance(object).toPrintable());

    }
}
