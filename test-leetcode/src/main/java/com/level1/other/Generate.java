package com.level1.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 *   [1,4,6,4,1]
 * [1,5,10,10,5,1]
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xncfnv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows > 0) {
            for (int row = 1; row <= numRows; row++) {
                List<Integer> list = new ArrayList<>(row);
                lists.add(list);
                list.add(1);
                if (row == 1) {
                    continue;
                }else {
                    List<Integer> preList = lists.get(row - 2);
                    for (int i = 0; i < preList.size() - 1; i++) {
                        list.add(preList.get(i) + preList.get(i + 1));
                    }
                    list.add(1);
                }
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new Generate().generate(5);
        System.out.println(list);
    }
}
