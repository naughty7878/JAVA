package com.test.concurrent.interrupt;

public class TestInterrupt3 {

    public static void main(String[] args) {
        MyThread3 thread=new MyThread3();
        thread.start();
        thread.interrupt();
        //sleep等待一秒，等myThread运行完
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("myThread线程是否存活："+thread.isAlive());
    }

}

class MyThread3 extends Thread {

    @Override
    public void run() {
        long num = 1;
        for (int i = 1; i < 1000000000; i++) {
            num = num * (num + i) % 1000000000;
            if (this.isInterrupted()) {
                System.out.println("通过this.isInterrupted()检测到中断");
                System.out.println("第一个interrupted()" + Thread.interrupted());
                System.out.println("第二个interrupted()" + Thread.interrupted());
                System.out.println("因为检测到中断，所以跳出循环，线程到这里结束，因为后面没有内容了");
                return;
            }
        }
        System.out.println("结果是" + num);
        System.out.println("退出线程" + Thread.currentThread().getName());
    }
}