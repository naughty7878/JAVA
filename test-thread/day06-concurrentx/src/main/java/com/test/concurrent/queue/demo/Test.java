package com.test.concurrent.queue.demo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

    private static final ThreadLocalRandom RANDOM =
            ThreadLocalRandom.current();

    public static void main(String[] args) {

        int i = 16;
        System.out.println((i >>= 1) != 0);

//        for (int i = 0; i < 10; i++) {
////            ThreadLocalRandom random = ThreadLocalRandom.current();
////            int i1 = random.nextInt(100);
////            System.out.println(i1);
////            System.out.println(random);
//
//            new Player().start();
//        }


    }

    private static class Player extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + ": " + RANDOM.nextInt(100));
        }
    }

}




