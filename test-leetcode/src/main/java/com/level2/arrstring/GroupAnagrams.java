package com.level2.arrstring;

import org.junit.Test;

import java.util.*;

/**
 * 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * 相关标签
 * 哈希表
 * 字符串
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvaszc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> list = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
//            List<String> strings = map.computeIfAbsent(key, k -> new ArrayList<String>());
            List<String> strings = map.get(key);
            if (strings == null) {
                strings = new ArrayList<String>();
                map.put(key, strings);
                list.add(strings);
            }
            strings.add(str);
        }
        return list;
    }

    public static void main(String[] args) {
        // ["eat", "tea", "tan", "ate", "nat", "bat"]
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new GroupAnagrams().groupAnagrams(strs);
        System.out.println(lists);
    }
    
    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");
        String bb = map.getOrDefault("bb", "bb");
        System.out.println("bb = " + bb);
        String cc = map.putIfAbsent("cc", "cc");
        System.out.println("cc = " + cc);
        String aa = map.putIfAbsent("aa", "aaa");
        System.out.println("aa = " + aa);
        String dd = map.computeIfAbsent("dd", key -> "ddd");
        System.out.println("dd = " + dd);
        System.out.println("map = " + map);
    }
}
