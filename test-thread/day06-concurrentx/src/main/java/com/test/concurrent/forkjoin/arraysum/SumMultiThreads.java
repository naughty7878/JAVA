package com.test.concurrent.forkjoin.arraysum;


import com.test.concurrent.forkjoin.utils.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程统计数组计算结果，平均分配计算量给每个线程
 */
public class SumMultiThreads {
    public final static int NUM = 10000;

    public static long sum(int[] arr, ExecutorService executor) throws Exception {
        long result = 0;
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        SumTask[] tasks = new SumTask[numThreads];
        Future<Long>[] sums = new Future[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new SumTask(arr, (i * NUM), ((i + 1) * NUM));
            sums[i] = executor.submit(tasks[i]);
        }

        for (int i = 0; i < numThreads; i++) {
            result += sums[i].get();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = Utils.buildRandomIntArray(1000000);
        //每个线程需计算1000个任务,根据任务总量计算要创建的线程数量
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        System.out.printf("The array length is: %d\n Thread num: %d\n", arr.length,numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long result = sum(arr, executor);

        System.out.printf("The result is: %d\n", result);

        executor.shutdown();
    }
}
