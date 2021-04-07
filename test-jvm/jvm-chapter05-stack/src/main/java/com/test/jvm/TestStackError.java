package com.test.jvm;

public class TestStackError {

    private static int count = 1;

    /**
     * 演示栈中的异常： StackOverflowError 异常
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(count);
        count ++;
        main(args);
    }
}
