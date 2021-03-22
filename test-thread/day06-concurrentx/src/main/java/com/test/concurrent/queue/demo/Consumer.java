package com.test.concurrent.queue.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    private BlockingQueue<Integer> numbersQueue;



    public Consumer(BlockingQueue<Integer> numbersQueue) {
        this.numbersQueue = numbersQueue;
    }
    public void run() {
        try {
            while(true){

//                while (TestDemo.flag != 1) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

                // 消费
                Integer num = numbersQueue.take();
                System.out.printf("%s：将%d号球从队列中取出，队列中还存在%d个球\n", Thread.currentThread().getName() ,num, numbersQueue.size());

                Thread.sleep(10);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
