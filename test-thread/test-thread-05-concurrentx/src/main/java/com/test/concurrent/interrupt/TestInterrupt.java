package com.test.concurrent.interrupt;

import java.util.ArrayList;
import java.util.List;

public class TestInterrupt {

    public static void main(String[] args ) {
        MyThread thread=new MyThread();
        thread.start();
        thread.interrupt();
        // 测试 isInterrupted()函数
        System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        // 测试 interrupted()函数
        System.out.println("第一次调用thread.interrupted()："+Thread.interrupted());
        System.out.println("第二次调用thread.interrupted()："+Thread.interrupted());
        System.out.println("thread是否存活："+thread.isAlive());
    }
}

class MyThread extends Thread {
    @Override
    public  void run() {
        long num = 1;
        for (int i = 1; i < 1000000000; i++) {
            num = num * (num + i) % 1000000000;
        }
        System.out.println("结果是" + num);
        System.out.println("退出线程" + Thread.currentThread().getName());
    }
}
