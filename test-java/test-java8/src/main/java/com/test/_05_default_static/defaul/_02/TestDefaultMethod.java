package com.test._05_default_static.defaul._02;

/**
 * 参考：https://blog.csdn.net/u010003835/article/details/76850242
 * @author h__d
 */
interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}

interface InterfaceB extends InterfaceA {
}

interface InterfaceC extends InterfaceA {
    @Override
    default void foo() {
        System.out.println("InterfaceC foo");
    }
}

interface InterfaceD extends InterfaceA {
    @Override
    void foo();
}

/**
 * @author h__d
 */
public class TestDefaultMethod {
    public static void main(String[] args) {
        new InterfaceB() {}.foo(); // 打印：“InterfaceA foo”
        new InterfaceC() {}.foo(); // 打印：“InterfaceC foo”
        new InterfaceD() {
            @Override
            public void foo() {
                System.out.println("InterfaceD foo");
            }
        }.foo(); // 打印：“InterfaceD foo”

        // 或者使用 lambda 表达式
        ((InterfaceD) () -> System.out.println("InterfaceD foo")).foo();
    }
}
