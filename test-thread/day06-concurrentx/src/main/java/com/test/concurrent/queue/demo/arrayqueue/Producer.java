package com.test.concurrent.queue.demo.arrayqueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private BlockingQueue<Integer> numbersQueue;

    public Producer(BlockingQueue<Integer> numbersQueue) {
        this.numbersQueue = numbersQueue;
    }
    @Override
    public void run() {
        try {
            while(true){
                int num = ThreadLocalRandom.current().nextInt(100);
                numbersQueue.put(num);
                System.out.printf("%s：将%d号球放入队列中，存在%d个球\n",Thread.currentThread().getName(), num, numbersQueue.size());
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
