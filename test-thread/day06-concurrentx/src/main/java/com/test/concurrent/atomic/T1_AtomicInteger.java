package com.test.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：杨过
 * @date ：Created in 2020/7/3
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class T1_AtomicInteger {

    public static int total = 0;
    static AtomicInteger atomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    /*synchronized () {
                        total++;//cas
                    }*/
                    atomic.getAndIncrement();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println(atomic.get());
    }
}
