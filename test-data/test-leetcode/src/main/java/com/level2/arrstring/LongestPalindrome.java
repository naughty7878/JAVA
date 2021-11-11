package com.level2.arrstring;

/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 * 相关标签
 * 字符串
 * 动态规划
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvn3ke/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String longestStr = s.substring(0, 1);
        int longest = 1;
        int len = s.length();
        for (int i = 0; i < len - 1 && longest < len; i++) {
            for (int j = 1; i + j < len && i - j > -1; j++) {
                if (s.charAt( i - j ) == s.charAt(i + j)){
                    if (j * 2 + 1> longest) {
                        longestStr = s.substring(i - j, i + j + 1);
                        longest = j * 2 + 1;
                    }
                } else {
                    break;
                }
            }
            for (int j = 0; i + j + 1< len && i - j > -1; j++) {
                if (s.charAt( i - j ) == s.charAt(i + j + 1)) {
                    if (j * 2 + 2 > longest) {
                        longestStr = s.substring(i - j, i + j + 2);
                        longest = j * 2 + 2;
                    }
                } else {
                    break;
                }
            }
        }
        return longestStr;
    }

    public static void main(String[] args) {
        String longestPalindrome = new LongestPalindrome().longestPalindrome("aaaa");
        System.out.println("longestPalindrome = " + longestPalindrome);

        System.out.println("abc = " + "abc".substring(0, 3));
    }
}
