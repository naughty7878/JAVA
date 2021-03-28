package com.test.concurrent.unsafe;

import com.test.concurrent.util.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ThreadParkerRunner {

    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread - is running----");
                //true则会实现ms定时,false则会实现ns定时。
                unsafe.park(false,0L); //阻塞当前线程
                System.out.println("thread is over-----");
            }
        });

        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("唤醒Thread-t");
        unsafe.unpark(t);

    }

}
