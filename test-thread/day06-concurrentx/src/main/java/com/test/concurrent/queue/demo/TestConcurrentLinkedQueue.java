package com.test.concurrent.queue.demo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestConcurrentLinkedQueue {

    public static void main(String[] args) throws InterruptedException {


        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 1; i++) {
            int finalI = i;
            new Thread(() -> {
                queue.offer(finalI);
                queue.offer(finalI+1);
                queue.offer(finalI+2);
//                System.out.println("生产：" + finalI);
            }).start();
        }

        Thread.sleep(5000);

        for (int j = 0; j < 1; j++) {
            int finalI = j;
            new Thread(() -> {
                Integer e1 = queue.poll();
                Integer e2 = queue.poll();
                Integer e3 = queue.poll();
                System.out.println("消费1：" + e1);
                System.out.println("消费2：" + e2);
                System.out.println("消费3：" + e3);
            }).start();
        }

    }

}
