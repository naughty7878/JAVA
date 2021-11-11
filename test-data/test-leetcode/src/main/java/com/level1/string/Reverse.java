package com.level1.string;

/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * <p>
 * 输入：x = 0
 * 输出：0
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnx13t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Reverse {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int temp = x % 10;
            x /= 10;
            if (rev  > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            if (rev  < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            rev = rev * 10 + temp;
        }
        return rev;
    }

    public static void main(String[] args) {
        int x = Integer.MIN_VALUE;
        System.out.println(x);
        System.out.println(new Reverse().reverse(x));
//        System.out.println(-8 % 10);
    }
}
