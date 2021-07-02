package com.test.first._05_default_static.stati;

public class TestStaticMethod {
    public static void main(String[] args) {
        InterfaceA.foo();
    }
}


interface InterfaceA {
    static void foo(){
        System.out.println("InterfaceA foo");
    }
}