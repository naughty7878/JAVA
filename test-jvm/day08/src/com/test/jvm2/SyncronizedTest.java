package com.test.jvm2;

public class SyncronizedTest {
    public void f(){
        Object o = new Object();
        synchronized (o) {
            System.out.println(o);
        }
    }
}
