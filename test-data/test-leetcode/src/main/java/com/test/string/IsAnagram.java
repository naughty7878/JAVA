package com.test.string;

import java.util.Arrays;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IsAnagram {

    // 方法二
    public boolean isAnagram(String s, String t) {
        // 边界条件判断
        if(s == null || s.length() < 1
                || t == null || t.length() < 1) {
            return false;
        }else if(s.length() != t.length()) {
            return false;
        }
        // 26个字母
        int[] table = new int[26];
        for(int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for(int j = 0; j < t.length(); j++) {
            table[t.charAt(j) - 'a']--;
            if( table[t.charAt(j) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s = "anagram", t = "nagaram";
        String s = "rat", t = "car";
        boolean isAnagram = new IsAnagram().isAnagram(s, t);
        System.out.println(isAnagram);
    }


    // 方法一
    public boolean isAnagram1(String s, String t) {
        // 边界条件判断
        if(s == null || s.length() < 1
                || t == null || t.length() < 1) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        // 排序
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        // 判断
        return Arrays.equals(sChars, tChars);
    }
}
