package com.level1.array;

import java.util.Arrays;

/**
 * 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ContainsDuplicate {

    // 方法一：循环遍历排序
    public boolean containsDuplicate(int[] nums) {
        // 边界条件判断
        if(nums == null || nums.length <= 1) {
            return  false;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++ ){
            if(nums[i] == nums[i+1]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,14,18,22,22};
        boolean b = new ContainsDuplicate().containsDuplicate(nums);
        System.out.println(b);
    }

    // 方法一：循环遍历，超时
    public boolean containsDuplicate1(int[] nums) {
        // 边界条件判断
        if(nums == null || nums.length <= 1) {
            return  false;
        }
        for(int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j ++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
