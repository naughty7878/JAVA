package com.hd.demo;

/**
 * 例子：创建5个窗口卖票，总票数为100张，使用继承Thread类的方式
 *
 * 关于同步方法的总结：
 * 1、同步方法仍然涉及到同步监视器，只是不需要我们明显的声明
 * 2、非静态的同步方法，同步监视器是：this
 *    静态的同步方法，监视器是：当前类本身 Object.class
 */

class Window3 extends Thread {

    // 总票数
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (show()) {

            } else {
                break;
            }
        }
    }

    // 同步监视器是当前类对象 Window3.class
    private static synchronized boolean show(){
        // 同步监视器是：t1、t2、t3..此种解决方式是错误的
        // private synchronized boolean show(){
        if (tickets > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + tickets);
            tickets--;
            return true;
        }
        return false;
    }
}

public class TicketDemo3 {

    public static void main(String[] args) {
        Window3 w1 = new Window3();
        Window3 w2 = new Window3();
        Window3 w3 = new Window3();
        Window3 w4 = new Window3();
        Window3 w5 = new Window3();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w4.setName("窗口4");
        w5.setName("窗口5");

        w1.start();
        w2.start();
        w3.start();
        w4.start();
        w5.start();
    }
}
