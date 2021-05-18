package com.test.jmm.test;

public class AtomicTest {

    private static int counter = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    synchronized (AtomicTest.class){
                        counter++;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " Over~~~");
            });
            thread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);

    }
}
