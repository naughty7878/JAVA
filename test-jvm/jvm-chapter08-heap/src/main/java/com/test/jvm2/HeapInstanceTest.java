package com.test.jvm2;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 */
public class HeapInstanceTest {
    byte[] buffe = new byte[new Random().nextInt(1024 * 1024)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
