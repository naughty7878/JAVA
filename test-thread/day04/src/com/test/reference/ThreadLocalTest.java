package com.test.reference;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<>();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tl.set("ABC");
                // 当前线程共享变量为ABC，Thread-1  Thread-0  ABC
                System.out.println(Thread.currentThread().getName() + "  " + tl.get());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 当前线程共享变量为空，Thread-1  null
                System.out.println(Thread.currentThread().getName() + "  " + tl.get());
            }
        }.start();
    }
}
