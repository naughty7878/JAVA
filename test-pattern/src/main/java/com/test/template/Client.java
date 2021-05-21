package com.test.template;

public class Client {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.templateMethod();
    }
}

// 抽象类
abstract class AbstractClass {
    // 模板方法
    public void templateMethod() {
        specificMethod();
        abstractMethod1();
        abstractMethod2();
        hookMethod();
    }

    // 具体方法
    protected void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    // 抽象方法1
    public abstract void abstractMethod1();

    // 抽象方法2
    public abstract void abstractMethod2();

    // 勾子方法
    void hookMethod() {
    }
}

// 具体子类
class ConcreteClass extends AbstractClass {
    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}