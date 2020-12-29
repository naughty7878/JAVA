package com.test.sync;

import java.util.concurrent.atomic.AtomicStampedReference;

public class TestClearLock {
    public static void main(String[] args) {
        Object obj = new Object();
        // 粗化前
        synchronized (obj) {
            System.out.println("111");
        }
        synchronized (obj) {
            System.out.println("222");
        }
        synchronized (obj) {
            System.out.println("333");
        }


    }
}
