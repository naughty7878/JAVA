package com.test.first._05_default_static.defaul._06;

interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
 
    default void bar() {
        System.out.println("InterfaceA bar");
    }
}
 
abstract class AbstractClassA {
    public abstract void foo();
 
    public void bar() {
        System.out.println("AbstractClassA bar");
    }
}
 
class ClassA extends AbstractClassA implements InterfaceA {
    @Override
    public void foo() {
        InterfaceA.super.foo();
    }
}
 
public class TestDefualtMethod {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.foo(); // 打印：“InterfaceA foo”
        classA.bar(); // 打印：“AbstractClassA bar”
    }
}