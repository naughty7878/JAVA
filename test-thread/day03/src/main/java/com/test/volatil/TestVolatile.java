package com.test.volatil;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

    static boolean flage = true;

    public static void main(String[] args) {
        final TestVolatile v = new TestVolatile();
        new Thread(){
            @Override
            public void run() {
                v.hello();
            }
        }.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.flage = false;
        System.out.println(flage);
    }

    public void hello(){
        System.out.println("hello start ...");
        while(flage){
//            System.out.println(Thread.currentThread().getName());
        }
        System.out.println("hello end ...");
    }
}
