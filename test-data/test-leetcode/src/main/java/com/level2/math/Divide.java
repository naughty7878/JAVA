package com.level2.math;

/**
 * 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 * <p>
 * 提示：
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * 相关标签
 * 位运算
 * 数学
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwp6vi/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Divide {

    public int divide(int dividend, int divisor) {
        // 边界判断
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) return -dividend;
            return Integer.MAX_VALUE;
        }
        // 转long类型
        long a = dividend;
        long b = divisor;
        int sign = 1;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long res = div(a, b);
        if (sign > 0) {
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        }
        return (int) -res;
    }

    private long div(long a, long b) {
        if (a < b) {
            return 0;
        }
        long count = 1;
        long tb = b;
        while ((tb + tb) <= a) {
            count = count + count;
            tb = tb + tb;
        }
        return count + div(a - tb, b);
    }

    public static void main(String[] args) {
        int divide = new Divide().divide(7, -3);
        System.out.println("divide = " + divide);
//        System.out.println(Math.abs(Integer.MAX_VALUE));
//        System.out.println(Math.abs(Integer.MIN_VALUE) * -1);
    }
}
