package com.test.concurrent.pool.pool.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

public class FastAccumulator {

    public static int THREAD_NUM = 10;
    public static int COUNT_NUM = 100_000_000;
    public static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    // 方案一：使用 普通遍历 + synchronized关键字
    public static Integer count = 0;
    // 方案二：使用 AtomicInteger 并发原子类
//    public static AtomicInteger count = new AtomicInteger();
    // 方案三：使用 juc包中的 LongAdder
//    public static LongAdder count = new LongAdder();
    // 方案四：使用 juc包中的 LongAdder
//    public static LongAccumulator count = new LongAccumulator((left, right) -> left + right,0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                for (int j = 0; j < COUNT_NUM; j++) {
                    // 方案一
                    synchronized (FastAccumulator.class) {
                        count += 2;
                    }
                    // 方案二
//                    count.addAndGet(2);
                    // 方案三
//                    count.add(2);
                    // 方案四
//                    count.accumulate(2);

                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("耗时：" + (end - start));
    }
}


