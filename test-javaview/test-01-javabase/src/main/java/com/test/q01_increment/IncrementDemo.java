package com.test.q01_increment;

/**
 *  面试题一：考验自增问题
 *
 *  输出结果是什么
 */
public class IncrementDemo {

    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("k = " + k);
    }
}
