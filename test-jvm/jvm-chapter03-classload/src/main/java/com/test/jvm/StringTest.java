package com.test.jvm;

public class StringTest {
    public static void main(String[] args) {
        java.lang.String str = new java.lang.String();
        System.out.println("hello world");

        StringTest test = new StringTest();
        System.out.println(test.getClass().getClassLoader());
    }
}
