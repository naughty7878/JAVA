package com.test.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * -XX:+PrintFlagsFinal
 */
public class TestInitial {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        //打印对象头
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}

class A {
//    int a = 1;
}