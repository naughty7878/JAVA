package com.test.q05_cyclic;

/**
 * 编程题：有n步台阶，一次只能上1步或者2步，共有多少种走法？
 *
 * 递归的方法
 *
 * 1级 -->  f(1) == 1
 * 2级 -->  f(2) == f(1) + 1
 * 3级 -->  f(2) == f(2) + f(1)
 * ------
 * n级 -->  f(n) == f(n-1) + f(n-2)
 */
public class StapDemo1 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("结果是：" + f(40));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }


    public static long f(long n) {
        if(n < 1)
            throw new RuntimeException("台阶数量不能少于1");
        if(n == 1 || n == 2) {
            return n;
        }
        return f(n - 1) + f(n - 2);
    }
}
