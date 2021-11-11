package com.level2.sort;

/**
 * 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *  
 * <p>
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
 * <p>
 * 相关标签
 * 数组
 * 二分查找
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv4hjg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }






    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int peakElement = new FindPeakElement().findPeakElement(nums);
        System.out.println("peakElement = " + peakElement);
    }

    public int findPeakElement2(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            boolean left = true;
            if (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
                left = false;
            }
            boolean right = true;
            if (i + 1 < length && nums[i + 1] >= nums[i]) {
                right = false;
            }
            if (left && right) {
                return i;
            }
        }
        return 0;
    }

    public int findPeakElement3(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}
