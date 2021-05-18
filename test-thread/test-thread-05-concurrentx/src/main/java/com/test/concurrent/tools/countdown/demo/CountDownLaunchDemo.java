package com.test.concurrent.tools.countdown.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLaunchDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
                countDownLatch.countDown();
                System.out.println("2");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("11");
                countDownLatch.countDown();
                System.out.println("22");
            }
        }).start();

        countDownLatch.await();
        System.out.println("3");
    }

}
