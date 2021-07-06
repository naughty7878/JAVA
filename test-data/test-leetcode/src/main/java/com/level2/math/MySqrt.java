package com.level2.math;

/**
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * 相关标签
 * 数学
 * 二分查找
 *
 * Java
 *
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwrzwc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MySqrt {

    public int mySqrt(int x) {
        int min = 0;
        int max = x;
        if (x <= 1) {
            min = x;
            max = 1;
        }
        // 差值在 1 的时候介绍
        while (min + 1 < max) {
            // 二分查找法，每次去min 与 max 的中间值计算
            long num = (min + max) / 2;
            long sqrt = num * num;
            if (sqrt <= x) {
                min = (int) num;
            } else {
                max = (int) num;
            }
            System.out.println("min = " + min);
            System.out.println("max = " + max);
            System.out.println("sqrt = " + sqrt);
        }
        return min;
    }

    public double mySqrt2(int x, double cha) {
        double min = 0;
        double max = x;
        if (x <= 1) {
            min = x;
            max = 1;
        }
        while (min + cha < max) {
            // 二分查找法，每次去min 与 max 的中间值计算
            double num = (min + max) / 2;
            double sqrt = num * num;
            if (sqrt <= x) {
                min = num;
            } else {
                max = num;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int mySqrt = new MySqrt().mySqrt(2147395599);
        System.out.println("mySqrt = " + mySqrt);

        double mySqrt2 = new MySqrt().mySqrt2(2, 0.00001);
        System.out.println("mySqrt2 = " + mySqrt2);
    }
}
