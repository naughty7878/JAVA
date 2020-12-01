package com.hd.demo;


class Window4 implements Runnable {

    private int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (show()) {
            } else {
                break;
            }
        }
    }

    // 同步监视器是：this
    private synchronized boolean show() {
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

public class TicketDemo4 {
    public static void main(String[] args) {
        Window4 window = new Window4();
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
