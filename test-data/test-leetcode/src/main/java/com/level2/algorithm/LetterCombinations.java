package com.level2.algorithm;

import java.util.*;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv8ka1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return list;
        }
        char[] chars = digits.toCharArray();
        list.add("");

        Map<Integer, List<String>> map = initMap();
        for (char aChar : chars) {

            List<String> strings = map.get(Integer.valueOf(aChar + ""));
            List<String> tempList = new ArrayList<>();
            for (String str : strings) {

                for (String l : list) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(l);
                    stringBuilder.append(str);
                    tempList.add(stringBuilder.toString());
                }

            }
            list = tempList;
        }
        return list;
    }

    public Map<Integer, List<String>> initMap() {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(2, Arrays.asList("a", "b", "c"));
        map.put(3, Arrays.asList("d", "e", "f"));
        map.put(4, Arrays.asList("g", "h", "i"));
        map.put(5, Arrays.asList("j", "k", "l"));
        map.put(6, Arrays.asList("m", "n", "o"));
        map.put(7, Arrays.asList("p", "q", "r", "s"));
        map.put(8, Arrays.asList("t", "u", "v"));
        map.put(9, Arrays.asList("w", "x", "y", "z"));
        return map;
    }

    public static void main(String[] args) {
        List<String> strings = new LetterCombinations().letterCombinations("23");
        System.out.println(strings);
    }

}
