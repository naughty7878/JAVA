package com.test.jvm2;


class Father {
    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}

class Son extends Father {
    public Son() {
        //invokespecial
        super();
    }

    public Son(int age) {
        //invokespecial
        this();
    }

    //不是重写的父类的静态方法，因为静态方法不能被重写！
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private" + str);
    }

    public void show() {
        //invokestatic
        showStatic("atguigu.com");

        //invokestatic
        super.showStatic("good!");

        //invokespecial
        showPrivate("hello!");

        //invokevirtual
        //虽然字节码指令中显示为invokevirtual，但因为此方法声明有final，不能被子类重写，所以也认为此方法是非虚方法。
        showFinal();

        //invokespecial
        super.showCommon();

        //invokevirtual
        //有可能子类会重写父类的showCommon()方法
        showCommon();
        info();

        MethodInterface in = null;
        //invokeinterface
        in.methodA();
    }

    public void info() {

    }

    public void display(Father f) {
        f.showCommon();
    }


}

interface MethodInterface {
    void methodA();
}

public class SonTest {

    public static void main(String[] args) {
        Son so = new Son();
        so.show();
    }
}
