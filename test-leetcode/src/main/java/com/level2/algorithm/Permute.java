package com.level2.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * 相关标签
 * 回溯算法
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvqup5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, nums.length);
    }

    private List<List<Integer>> permute(int[] nums, int length) {

        List<List<Integer>> result = new ArrayList<>();
        if (length == 1) {
            result.add(Arrays.asList(nums[0]));
            return result;
        } else {
            List<List<Integer>> lists = permute(nums, length - 1);
            for (List<Integer> list : lists) {
                for (int i = 0; i <= list.size(); i++) {
                    List<Integer> newlist = list.stream().collect(Collectors.toList());
                    newlist.add(i, nums[length - 1]);
                    result.add(newlist);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = new Permute().permute(nums);
        System.out.println("lists = " + lists);
    }
}
