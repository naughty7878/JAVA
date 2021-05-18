package com.hd.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 *  1、提高响应速度(减少了创建新线程的时间)
 *  2、降低资源消耗(重复利用线程池中线程，不需要每次都创建)
 *  3、便于线程管理
 *      1）corePoolSize:核心池的大小
 *      2）maximumPoolSize:最大线程数
 *      3）keepAliveTime:线程没有任务时最多保持多长时间后会终止
 *      。。。
 *
 *  面试题：创建多线程有几种方式？四种
 */

class NumberThread implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);

            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // 1、提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 2、执行指定的线程的操作，需要提供实现Runnable接口对象
        // 适合适用于Runnable
        service.execute(new NumberThread());
        // 适合适用于Callable
//        service.submit(Callable callable)

        service.shutdown();
    }
}
