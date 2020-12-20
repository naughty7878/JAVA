package com.hd.thread;

/**
 * 多线程的创建
 * 方式一：继承Thread类
 *  1、创建一个继承Thread类的子类
 *  2、重写Thread类的run()方法
 *  3、创建Thread类的子类对象
 *  4、通过此对象调用start()
 *
 *  例子：遍历100以内的偶数
 *
 */

// 1、创建一个继承Thread类的子类
class MyThread extends Thread {

    // 2、重写Thread类的run()方法
    @Override
    public void run() {
        for(int i = 0; i < 100; i ++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()  + "---" + i);
            }
        }
    }

}


public class ThreadTest1 {
    public static void main(String[] args) {
        // 3、创建Thread类的子类对象
        MyThread myThread = new MyThread();

        // 4、通过此对象调用start():
        // 1) 启动该线程
        // 2）调用当前线程的run()方法
        myThread.start();

        // 问题一：直接调用run()方法，程序会当作普通方法执行，不会启动另外一个线程
        // myThread.run();

        // 问题二：2次调用start()方法，不可以让已经start()线程去执行，回报异常IllegalThreadStateException，线程状态不对
        // myThread.start();

        System.out.println(Thread.currentThread().getName() + "---" + "Hello World");
    }
}
