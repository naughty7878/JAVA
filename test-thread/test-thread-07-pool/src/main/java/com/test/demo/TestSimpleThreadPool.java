package com.test.demo;

public class TestSimpleThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new DefaultThreadPool();
        threadPool.execute(() -> {
            System.out.println("abc");
        });
        threadPool.shutdown();
    }
}
