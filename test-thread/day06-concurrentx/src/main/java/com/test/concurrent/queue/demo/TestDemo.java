package com.test.concurrent.queue.demo;

import com.test.concurrent.queue.NumbersConsumer;
import com.test.concurrent.queue.NumbersProducer;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestDemo {

    public static volatile int flag = 0;

    public static void main(String[] args) {

        int BOUND = 10;
        int N_PRODUCERS = 10;
        int N_CONSUMERS = 10;

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(BOUND);

        for (int i = 0; i < N_PRODUCERS; i++) {
            new Thread(new Producer(queue), "生产者" + i).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new Consumer(queue), "消费者" + j).start();
        }


        // 控制消费者
        // 输入不等于1 继续等待
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                flag = scanner.nextInt();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
