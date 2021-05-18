package com.test.reference;

public class ThreadLocalTest2 {
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return Thread.currentThread().getName();
            }
        };

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tl.set("ABC");
                System.out.println(Thread.currentThread().getName() + "  " + tl.get());
                tl.remove();
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
                System.out.println(Thread.currentThread().getName() + "  " + tl.get());
            }
        }.start();
    }

}
