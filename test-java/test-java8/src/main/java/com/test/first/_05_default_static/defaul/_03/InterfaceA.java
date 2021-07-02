package com.test.first._05_default_static.defaul._03;

interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}
 
interface InterfaceB {
    default void bar() {
        System.out.println("InterfaceB bar");
    }
}
 
interface InterfaceC {
    default void foo() {
        System.out.println("InterfaceC foo");
    }
    
    default void bar() {
        System.out.println("InterfaceC bar");
    }
}
 
class ClassA implements InterfaceA, InterfaceB {
}
 
// 错误
//class ClassB implements InterfaceB, InterfaceC {
//}
 
class ClassB implements InterfaceB, InterfaceC {
    @Override
    public void bar() {
        InterfaceB.super.bar(); // 调用 InterfaceB 的 bar 方法
        InterfaceC.super.bar(); // 调用 InterfaceC 的 bar 方法
        System.out.println("ClassB bar"); // 做其他的事
    }
}