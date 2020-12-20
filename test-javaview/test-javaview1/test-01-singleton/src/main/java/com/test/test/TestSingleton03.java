package com.test.test;

import com.test.singleton.Singleton03;

public class TestSingleton03 {
    public static void main(String[] args) {
        Singleton03 instance = Singleton03.INSTANCE;
        System.out.println(instance.getInfo());
        System.out.println(instance);
    }
}
