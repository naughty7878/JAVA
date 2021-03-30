package com.test.concurrent.threadpood.pool;

import java.util.concurrent.*;

/**
 * @author ：杨过
 * @date ：Created in 2020/5/30
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class ThreadPool {

    public static void main(String[] args) {

        /*for (int i=0;i<300;i++){
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }).start();
        }*/
        //Executors.newCachedThreadPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i=0;i<6;i++){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i m task ："+Thread.currentThread().getName());
                }
            },i);
        }

        threadPoolExecutor.shutdown();  //running->shutdown
        threadPoolExecutor.shutdownNow(); //running->stop

    }


}
