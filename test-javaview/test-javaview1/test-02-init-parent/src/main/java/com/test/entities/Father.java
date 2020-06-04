package com.test.entities;

/**
 * 父类初始化<clinit>()方法
 * （1）j = method()
 * （2）父类的静态代码块
 *
 * 父类的实例化方法：
 * （1）super() （最前）
 *  (2) i = test();
 *  (3) 父类的非静态代码块
 *  (4) 父类的无参构造（最后）
 */
public class Father {

    private int i = test();
    private static int j = method();

    static{
        System.out.println("(1)");
    }
    Father(){
        System.out.println("(2)");
    }
    {
        System.out.println("(3)");
    }

    public int test(){
        System.out.println("(4)");
        return 1;
    }
    public static int method(){
        System.out.println("(5)");
        return 1;
    }

    public static void main(String[] args) {
        // 5 1 4 3 2
        new Father();
    }
}
