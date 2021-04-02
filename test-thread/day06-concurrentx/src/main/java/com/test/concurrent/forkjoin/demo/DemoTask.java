package com.test.concurrent.forkjoin.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class DemoTask extends RecursiveTask<Integer> {
    // 阀值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public DemoTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            // 如果任务大于阀值，就分裂成两个子任务计算
            int middle = (start + end) / 2;

            DemoTask leftTask = new DemoTask(start, middle);
            DemoTask rightTask = new DemoTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到器结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
            if(sum >= 0) {
                throw new RuntimeException("~~~~~~");
            }
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生产一个计算任务，负责计算 1+2+3+4
        DemoTask task = new DemoTask(1, 4);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
