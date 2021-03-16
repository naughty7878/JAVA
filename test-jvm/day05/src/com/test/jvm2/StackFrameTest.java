package com.test.jvm2;

/**
 * 方法的结束方式分为两种：
 * 1、正常结束，以return为代表
 * 2、方法执行中出现未捕获的异常，以抛出异常结束
 */
public class StackFrameTest {
    public static void main(String[] args) {
        StackFrameTest test = new StackFrameTest();
        try {
            test.method1();
        } catch (Exception e) {

        }

        System.out.println("main()执行结束...");
    }

    public void method1(){
        System.out.println("method1()开始执行...");
        method2();
//        System.out.println(10 / 0);
        System.out.println("method1()即将结束...");
    }

    public int method2(){
        System.out.println("method2()开始执行...");
        int i = 10;
        int m = (int) method3();
        System.out.println("method2()即将结束...");
        return i + m;
    }

    public double method3(){
        System.out.println("method3()开始执行...");
        double j = 20.0;
        System.out.println("method3()即将结束...");
        return j;
    }

}
