package com.test.concurrent.queue.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TestPriorityBlockingQueue {


    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                queue.offer(finalI);
                System.out.println("生产：" + finalI);
            }).start();
        }

        Thread.sleep(2000);

        for (int j = 0; j < 5; j++) {
            int finalI = j;
            new Thread(() -> {
                try {
                    System.out.println("消费：" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
