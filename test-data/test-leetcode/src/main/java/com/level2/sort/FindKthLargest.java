package com.level2.sort;

import java.util.Arrays;

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 相关标签
 * 堆
 * 分治算法
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvsehe/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        // 初始化最大堆
        initMaxHeap(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        for (int i = 0; i < k - 1; i++) {
            nums = deleteMaxHeap(nums);
            System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        }
        return nums[0];
    }

    public int[] deleteMaxHeap(int[] nums) {
        if (nums.length > 0) {
            nums[0] = nums[nums.length - 1];
            nums = Arrays.copyOf(nums, nums.length - 1);
            seftDown(nums, 0);
        }
        return nums;
    }

    // 构建最大堆
    public void initMaxHeap(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = (nums.length - 1)/2; i >= 0; i--) {
            seftDown(nums, i);
        }
    }

    // 下浮
    public void seftDown(int[] nums, int index) {
        while (index < nums.length / 2) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int max = left;
            if (right < nums.length && nums[left] < nums[right]) {
                max = right;
            }
            if (nums[index] < nums[max]) {
                // 交换
                swap(nums, index, max);
                // 递归
                index = max;
            } else {
                break;
            }
        }
    }

    // 交换
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kthLargest = new FindKthLargest().findKthLargest(nums, 4);
        System.out.println("kthLargest = " + kthLargest);
    }
}
