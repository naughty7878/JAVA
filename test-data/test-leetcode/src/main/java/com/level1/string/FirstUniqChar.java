package com.level1.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn5z8r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        // 边界条件判断
        if(s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer count = map.getOrDefault(ch, 0);
            map.put(ch, ++count);
        }
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer count = map.get(ch);
            if(count.intValue() == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String str = "loveleetcode";
        int i = new FirstUniqChar().firstUniqChar(str);
        System.out.println(i);
    }
}
