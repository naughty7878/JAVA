package com.test.concurrent.interrupt;

public class TestInterrupt2 {

    public static void main(String[] args ) {
        Thread.currentThread().interrupt();
        // 测试 isInterrupted()函数
        System.out.println("第一次调用Thread.currentThread().isInterrupted()："+Thread.currentThread().isInterrupted());
        System.out.println("第二次调用Thread.currentThread()v.isInterrupted()："+Thread.currentThread().isInterrupted());
        // 测试 interrupted()函数
        System.out.println("第一次调用Thread().interrupted()："+Thread.interrupted());
        System.out.println("第二次调用Thread().interrupted()："+Thread.interrupted());
        System.out.println("Thread.currentThread()是否存活："+Thread.currentThread().isAlive());
    }

}

