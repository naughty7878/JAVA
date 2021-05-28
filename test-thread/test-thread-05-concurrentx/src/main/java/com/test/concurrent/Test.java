package com.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.getAndIncrement());
    }
}