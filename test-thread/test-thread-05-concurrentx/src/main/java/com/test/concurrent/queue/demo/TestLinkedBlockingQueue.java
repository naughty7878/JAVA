package com.test.concurrent.queue.demo;

import java.util.concurrent.LinkedBlockingQueue;

public class TestLinkedBlockingQueue {

    public static void main(String[] args) throws InterruptedException {


        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    queue.put(finalI);
                    System.out.println("生产：" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(2000);

        for (int j = 0; j < 5; j++) {
            int finalI = j;
            new Thread(() -> {
                try {
                    Integer e = queue.take();
                    System.out.println("消费：" + e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
