package com.test.concurrent.interrupt;

public class TestInterrupt4 {

    public static void main(String[] args) {
        MyThread4 thread=new MyThread4();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  除了给线程设置中断标志，还能将处于中断状态的线程唤醒
        thread.interrupt();
    }

}

class MyThread4 extends Thread {
    @Override
    public  void run() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程休眠时间为" + (end - start) + "毫秒");
    }
}

