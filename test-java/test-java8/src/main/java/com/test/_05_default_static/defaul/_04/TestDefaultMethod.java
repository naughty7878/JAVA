package com.test._05_default_static.defaul._04;


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

// 正确
class ClassA implements InterfaceA, InterfaceB {
}

class ClassB implements InterfaceA, InterfaceB {
    @Override
    public void foo() {
//        InterfaceA.super.foo(); // 错误
        InterfaceB.super.foo();
    }
}

public class TestDefaultMethod {
    public static void main(String[] args) {
        new ClassA().foo();
        new ClassB().foo();
    }
}
