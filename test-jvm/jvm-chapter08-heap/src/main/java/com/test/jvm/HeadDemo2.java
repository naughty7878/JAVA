package com.test.jvm;

public class HeadDemo2 {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
