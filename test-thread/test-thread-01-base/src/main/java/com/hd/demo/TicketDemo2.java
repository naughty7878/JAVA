package com.hd.demo;


class Window2 implements Runnable {

    private int tickets = 100;

    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            // 同步代码块
            // 方式一、自己定一个对象
            synchronized (obj) {
            // 方式二、使用唯一的窗口对象this
            // synchronized (this) {
                if(tickets >  0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }

        }
    }
}

public class TicketDemo2 {
    public static void main(String[] args) {
        Window2 window = new Window2();
        Thread w1 = new Thread(window);
        Thread w2 = new Thread(window);
        Thread w3 = new Thread(window);
        Thread w4 = new Thread(window);
        Thread w5 = new Thread(window);

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
