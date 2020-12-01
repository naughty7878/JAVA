package com.hd.demo;

/**
 * 例子：创建5个窗口卖票，总票数为100张，使用继承Thread类的方式
 */

class Window extends Thread {

    // 总票数
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if(tickets >  0) {
                System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + tickets);
                tickets--;
            } else {
                break;
            }
        }
    }
}
public class TicketDemo1 {

    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        Window w4 = new Window();
        Window w5 = new Window();

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
