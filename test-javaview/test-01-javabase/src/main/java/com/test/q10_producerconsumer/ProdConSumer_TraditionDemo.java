package com.test.q10_producerconsumer;


public class ProdConSumer_TraditionDemo {

    public static void main(String[] args) {
//        ProdConSumer_TraditionDemo demo = new ProdConSumer_TraditionDemo();
//        demo.test();

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    static class ShareData {
        private volatile int number = 0;

        public synchronized void increment() throws InterruptedException {
            // 判断
            while (number != 0) {
                // 等待
                wait();
            }
            // 增加
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            notifyAll();
        }

        public synchronized void decrement() throws InterruptedException {
            // 判断
            while (number == 0) {
                // 等待
                wait();
            }
            // 增加
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            notifyAll();
        }
    }


    public void test(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
