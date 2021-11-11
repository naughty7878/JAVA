package com.level2.math;

/**
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * 相关标签
 * 递归
 * 数学
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwo102/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MyPow {


    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }



    // 77 次方
    // 38 + 38 + 1次方 =》 38 * 2 + 1
    // 38 次方
    // 19 + 19 次方 =》 19 * 2 + 0   1 => 2
    // 19 次方
    // 9 + 9 + 1次方 =》 9 * 2 + 1   1 => 4
    // 9 次方
    // 4 + 4 + 1 次方 =》 4 * 2 + 1  1 => 8
    // 4 次方
    // 2 + 2 次方  =》 2 * 2 + 0     1 => 16
    // 2 次方
    // 1 + 1 次方
    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }


    public static void main(String[] args) {
        double v = new MyPow().myPow(2,1);
        System.out.println("v = " + v);
//        System.out.println("Math.pow(0, 0) = " + Math.pow(0, 0));
//        System.out.println("(1.0 == 1) = " + (1.0 == 1));
    }


    // 77 次方
    // 38 + 38 + 1次方
    // 38 次方
    // 19 + 19 次方
    // 19 次方
    // 9 + 9 + 1次方
    // 9 次方
    // 4 + 4 + 1 次方
    // 4 次方
    // 2 + 2 次方
    // 2 次方
    // 1 + 1 次方
    public double myPow1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul1(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
