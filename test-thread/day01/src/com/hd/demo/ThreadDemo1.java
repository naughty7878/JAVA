package com.hd.demo;

/**
 * 示例：创建2个线程，1个线程遍历100以内的偶数，1个线程遍历100以内的奇数
 */
public class ThreadDemo1 {

    public static void main(String[] args) {

        // 创建Thread类的匿名类
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();
    }

}
