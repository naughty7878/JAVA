package com.test.queue;

import java.util.PriorityQueue;

/**
 * 优先级队列
 * 数据存储使用的是数组存，结构是最小堆，即顶上元素最小
 */
public class TestPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(10, (Integer::compare));
        queue.add(1);
        queue.add(7);
        queue.add(8);
        queue.add(2);
        queue.add(3);
        queue.add(9);
        queue.add(5);
        queue.add(4);
        queue.add(6);

        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }
        System.out.println(queue);

    }
}
