package com.test.string;

/**
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。ƒ
 */
public class ReverseString {

    public void reverseString(char[] s) {
        // 边界条件判断
        if (s == null || s.length < 1) {
            return;
        }
        // 遍历数组
        for (int i = 0; i < s.length / 2; i++) {
            // i 对应交换的下标为 s.length -1 - i
            // 交换对应的值
            s[s.length - 1 - i] = (char) ((int) s[i] + (int) s[s.length - 1 - i]);
            s[i] = (char) ((int) s[s.length - 1 - i] - (int) s[i]);
            s[s.length - 1 - i] = (char) ((int) s[s.length - 1 - i] - (int) s[i]);
        }
        System.out.println(s);
    }


    public static void main(String[] args) {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        new ReverseString().reverseString(s);
    }
}