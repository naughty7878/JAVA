package com.test.concurrent.queue.demo;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSynchronousQueue {

    public static volatile int flag = 0;

    public static void main(String[] args) throws InterruptedException {


        SynchronousQueue<String> queue = new SynchronousQueue<>(true);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                String str = "i-" + finalI;
                try {
                    queue.put(str);
                    System.out.println("生产：" + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(2000);

        for (int j = 0; j < 0; j++) {
            int finalI = j;
            new Thread(() -> {
                String str = "i-" + finalI;
                try {
                    queue.take();
                    System.out.println("消费：" + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


//        // 控制消费者
//        // 输入不等于1 继续等待
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            try {
//                flag = scanner.nextInt();
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}
