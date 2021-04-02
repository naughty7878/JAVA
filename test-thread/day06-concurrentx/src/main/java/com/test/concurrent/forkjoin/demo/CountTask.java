package com.test.concurrent.forkjoin.demo;

import java.util.concurrent.*;

public class CountTask extends RecursiveTask<Integer> {
    // 阀值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        try {
            // 让计算慢一点
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            // 如果任务大于阀值，就分裂成两个子任务计算
            int middle = (start + end) / 2;

            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到器结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        // 可以观察任务，是那个线程执行的
        System.out.println(Thread.currentThread().getName() + "-> start = " + start + ", end = " + end);
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        // 生产一个计算任务，负责计算 1+2+3+4
        CountTask task = new CountTask(1, 100);
//        // 使用公共的线程
//        ForkJoinTask<Integer> result = task.fork();
        // 方案一：使用自定义的线程池，执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 跟踪线程池状态 toString() 方法
                System.out.println(forkJoinPool.toString());
            }
        }).start();
        try {
            // 打印结果
            System.out.println(result.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        // 方案二：使用invoke，相当于外部提交任务 + join内部任务
//        Integer result = forkJoinPool.invoke(task);
//        System.out.println(result);

        // 方案三：execute，提交任务，当是没有返回值
//        forkJoinPool.execute(task);

    }
}
