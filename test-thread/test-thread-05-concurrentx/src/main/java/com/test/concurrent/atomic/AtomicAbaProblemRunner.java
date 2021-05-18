package com.test.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
@Slf4j
public class AtomicAbaProblemRunner {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = atomicInteger.get();
                log.info("操作线程"+Thread.currentThread().getName()+"--修改前操作数值:"+a);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isCasSuccess = atomicInteger.compareAndSet(a,2);
                if(isCasSuccess){
                    log.info("操作线程"+Thread.currentThread().getName()+"--Cas修改后操作数值:"+atomicInteger.get());
                }else{
                    log.info("CAS修改失败");
                }
            }
        },"主线程");

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.incrementAndGet();// 1+1 = 2;
                log.info("操作线程"+Thread.currentThread().getName()+"--increase后值:"+atomicInteger.get());
                atomicInteger.decrementAndGet();// atomic-1 = 2-1;
                log.info("操作线程"+Thread.currentThread().getName()+"--decrease后值:"+atomicInteger.get());
            }
        },"干扰线程");

        main.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        other.start();

    }
}
