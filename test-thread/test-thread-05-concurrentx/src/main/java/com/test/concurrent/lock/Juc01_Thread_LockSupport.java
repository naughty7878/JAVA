package com.test.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：杨过
 * @date ：Created in 2020/4/28
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Slf4j
public class Juc01_Thread_LockSupport {

    public static void main(String[] args) {

        Thread t0 = new Thread(new Runnable() {

            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("{},开始执行!",current.getName());
                for(;;){//spin 自旋
                    log.info("准备park住当前线程：{}....",current.getName());
                    LockSupport.park();
//                    System.out.println("Thread.interrupted() == " + Thread.interrupted());
                    log.info("当前线程{}已经被唤醒....",current.getName());
                }
            }

        },"t0");

        t0.start();

        try {
            Thread.sleep(5000);
            log.info("准备唤醒{}线程!",t0.getName());
//            LockSupport.unpark(t0);

            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
