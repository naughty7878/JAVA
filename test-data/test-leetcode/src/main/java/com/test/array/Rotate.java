package com.test.array;

import java.util.Arrays;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 *  
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        // k 与 k % nums.length 的右移效果相同
        k = k % nums.length;

        // 边界条件判断
        if(nums == null || nums.length == 0
                || nums.length == 1 || k <= 0) {
            return;
        }

        for(int x = nums.length - 1; x >= nums.length - gcd(nums.length , k); --x){
            for(int j = 1; ; ++j){
                int y = (x + k * j)  % nums.length;
                if(y != x) {
                    // 交换
                    // nums[x] 为最新位置上的元素
                    nums[x] = nums[x] + nums[y];
                    nums[y] = nums[x] - nums[y];
                    nums[x] = nums[x] - nums[y];
                } else {
                    // 完成循环 交换
                    break;
                }
            }
        }
        Arrays.stream(nums).forEach(System.out::println);
    }

    // 最大公约数
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        new Rotate().rotate(nums, 4);
    }



    // 方法2：数组翻转
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    // 方法1：超时
    public void rotate1(int[] nums, int k) {
        // 边界条件判断
        if(nums == null || nums.length == 0
                || nums.length == 1 || k <= 0) {
            return;
        }
        for(int i = 0; i <  k; ++i){
            // 一次右移移动1位
            for(int x = nums.length - 1; x >  0; --x){
                int y = x -1;
                // 交换
                nums[x] = nums[x] + nums[y];
                nums[y] = nums[x] - nums[y];
                nums[x] = nums[x] - nums[y];
            }
        }
        Arrays.stream(nums).forEach(System.out::println);
    }
}
