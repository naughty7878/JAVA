package com.test.concurrent.tools.countdown.demo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangeDemo {

    static Exchanger<String> exchanger = new Exchanger<String>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String A = "银行流水A";
//                try {
//                    String exchange = exchanger.exchange(A);
//                    System.out.println(exchange);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";
                try {
                    String A = exchanger.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B)
                            + "\nA录入的数据是："+ A
                            + "\nB录入的数据是："+ B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
