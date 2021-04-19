package com.test._05_default_static.defaul._01;

/**
 * 参考：https://blog.csdn.net/u010003835/article/details/76850242
 * @author h__d
 */
public class TestDefaultMethod {
    public static void main(String[] args) {
        ClasA a = new ClasA();
        a.foo();
    }
}

class ClasA implements InterfaceA {}

interface InterfaceA {
    default void foo(){
        System.out.println("InterfaceA foo");
    }
}
