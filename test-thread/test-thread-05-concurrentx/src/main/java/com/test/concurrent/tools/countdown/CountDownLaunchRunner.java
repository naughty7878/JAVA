package com.test.concurrent.tools.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：图灵-杨过
 * @date：2019/7/4
 * @version: V1.0
 * @slogan:天下风云出我辈，一入代码岁月催 description：
 */
public class CountDownLaunchRunner {

    static int sub = 0;
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        /*new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();*/


        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object){
                        for(int j=0;j<1000;j++){
                            sub++;
                        }
                    }

                }
            });
        }
        Thread.sleep(3000);
        //等待线程池中的2个任务执行完毕，否则一直等待,zk分布式锁
        countDownLatch.countDown();
        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
    }

}
