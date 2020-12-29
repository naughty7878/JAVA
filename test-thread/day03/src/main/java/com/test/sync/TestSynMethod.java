package com.test.sync;

public class TestSynMethod {

    synchronized void hello() {

    }

    public static void main(String[] args) {
        String anything = "anything";
        synchronized (anything) {
            System.out.println("hello word");
        }
    }
}
