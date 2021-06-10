package com.test.recursion;

public class RecursionTest {

    public static void main(String[] args) {
        // 通过打印问题，回顾递归调用机制
//        test1(4);

        int result = test2(4);
        System.out.println("result = " + result);
    }

    // 打印问题
    public static void test1(int n){
        if (n > 2) {
            test1(n - 1);
        }
        System.out.println("n = " + n);
    }

    // 阶乘问题
    public static int test2(int n){
        if (n == 1) {
            return 1;
        }
        return test2(n - 1) * n;
    }
}
