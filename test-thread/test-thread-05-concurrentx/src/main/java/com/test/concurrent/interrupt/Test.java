package com.test.concurrent.interrupt;

public class Test {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "c";
        a = b = c + a;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

    }


}
