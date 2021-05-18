package com.test.concurrent.forkjoin.arraysum;


import com.test.concurrent.forkjoin.utils.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ：杨过
 * @date ：Created in 2020/9/2 13:55
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 任务切分,递归求和
 **/
public class SumRecursiveMT {
    public static class RecursiveSumTask implements Callable<Long> {
        /**
         * 切分任务的最小粒度
         */
        public static final int SEQUENTIAL_CUTOFF = 1;
        int lo;
        int hi;
        int[] arr; // arguments
        ExecutorService executorService;

        RecursiveSumTask( ExecutorService executorService, int[] a, int l, int h) {
            this.executorService = executorService;
            this.arr = a;
            this.lo = l;
            this.hi = h;
        }

        public Long call() throws Exception { // override
            System.out.format("%s range [%d-%d] begin to compute %n",
                    Thread.currentThread().getName(), lo, hi);
            long result = 0;
            if (hi - lo <= SEQUENTIAL_CUTOFF) {
                for (int i = lo; i < hi; i++)
                    result += arr[i];

                System.out.format("%s range [%d-%d] begin to finished %n",
                        Thread.currentThread().getName(), lo, hi);
            }
            else {
                RecursiveSumTask left = new RecursiveSumTask(executorService, arr, lo, (hi + lo) / 2);
                RecursiveSumTask right = new RecursiveSumTask(executorService, arr, (hi + lo) / 2, hi);
                Future<Long> lr = executorService.submit(left);
                Future<Long> rr = executorService.submit(right);

                result = lr.get() + rr.get();
                System.out.format("%s range [%d-%d] finished to compute %n",
                        Thread.currentThread().getName(), lo, hi);
            }

            return result;
        }
    }


    public static long sum(int[] arr) throws Exception {
        int nofProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //ExecutorService executorService = Executors.newCachedThreadPool();

        RecursiveSumTask task = new RecursiveSumTask(executorService, arr, 0, arr.length);
        long result =  executorService.submit(task).get();
        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = Utils.buildRandomIntArray(1000000);
        System.out.printf("The array length is: %d\n", arr.length);

        long result = sum(arr);

        System.out.printf("The result is: %d\n", result);

    }
}