package com.tencent.math;

/**
 * 2的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 *
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 *
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 *
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 *
 * 输入：n = 5
 * 输出：false
 * 
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *
 *
 * 进阶：你能够不使用循环/递归解决此问题吗？
 *
 * 相关标签
 * 位运算
 * 递归
 * 数学
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/tencent/x5yjhd/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        boolean powerOfTwo = new IsPowerOfTwo().isPowerOfTwo(15);
        System.out.println("powerOfTwo = " + powerOfTwo);
        System.out.println("Integer.toBinaryString(1) = " + Integer.toBinaryString(1));
        System.out.println("Integer.toBinaryString(2) = " + Integer.toBinaryString(2));
        System.out.println("Integer.toBinaryString(4) = " + Integer.toBinaryString(4));
        System.out.println("Integer.toBinaryString(8) = " + Integer.toBinaryString(8));

    }
}
