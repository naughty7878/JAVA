package com.test.concurrent.pool.schedule;

import java.util.concurrent.*;

public class TestScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<Integer> future1 = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟30s执行");
            return 1;
        }, 30_000, TimeUnit.MILLISECONDS);

        ScheduledFuture<Integer> future2 = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟20s执行");
            return 2;
        }, 20_000, TimeUnit.MILLISECONDS);

        ScheduledFuture<Integer> future3 = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟8s执行");
            return 1;
        }, 8_000, TimeUnit.MILLISECONDS);


        scheduledThreadPoolExecutor.shutdown();

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

//        while (!scheduledThreadPoolExecutor.isTerminated()) {
//            Thread.sleep(1000);
//        }
        System.out.println("Game Over~~~");


    }
}
