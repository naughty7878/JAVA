package com.test.q09_blockingqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {


    @Test
    public void test4() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.offer("a",3, TimeUnit.SECONDS);
        blockingQueue.offer("b",3, TimeUnit.SECONDS);
        blockingQueue.offer("c",3, TimeUnit.SECONDS);

//        blockingQueue.offer("d",3, TimeUnit.SECONDS);

//        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
    }

    @Test
    public void test3() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");

//        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    @Test
    public void test2() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

        @Test
    public void test1(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }
}
