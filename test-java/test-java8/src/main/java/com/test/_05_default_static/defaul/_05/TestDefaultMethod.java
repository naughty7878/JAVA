package com.test._05_default_static.defaul._05;

interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}

interface InterfaceB extends InterfaceA {
    @Override
    default void foo() {
        System.out.println("InterfaceB foo");
    }
}

interface InterfaceC extends InterfaceA {
    @Override
    default void foo() {
        InterfaceA.super.foo();
    }
}

class ClassA implements InterfaceB, InterfaceC {
    @Override
    public void foo() {
        InterfaceB.super.foo();
        InterfaceC.super.foo();
    }
}

public class TestDefaultMethod {
    public static void main(String[] args) {
        new ClassA().foo();
    }
}
