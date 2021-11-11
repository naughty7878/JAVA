package com.level2.other;

/**
 * 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: a = -2, b = 3
 * 输出: 1
 * 相关标签
 * 位运算
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xwaiag/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class GetSum {
    public int getSum(int a, int b) {
        do {
            System.out.println("a = " + Integer.toBinaryString(a));
            System.out.println("b = " + Integer.toBinaryString(b));
            int c = a & b;
            int d = a ^ b;
            a = c << 1;
            b = d;
        } while (a != 0);
        return b;
    }

    public static void main(String[] args) {
        int sum = new GetSum().getSum(-2, 3);
        System.out.println(sum);
    }
}
