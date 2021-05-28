package com.test.q10_producerconsumer;

public class TestVolatile {

    boolean found = false;

    public static void main(String[] args) {

        final TestVolatile testVolatile = new TestVolatile();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：等基友送笔来...");

                while (!testVolatile.found) {
//                    Thread.yield();
                }

                System.out.println(Thread.currentThread().getName() + "：笔来了，开始写字...");
            }
        }, "我的线程").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "：基友找到笔了，送过去...");
                testVolatile.found = true;
            }
        }, "基友线程").start();
    }
}