package com.level2.sort;

/**
 * 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *  
 *
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 *
 * 相关标签
 * 数组
 * 二分查找
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvyz1t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Search {

    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    public int search(int[] nums, int start, int end, int target) {
        if (start == end && nums[start] == target) {
            return start;
        } else if (start != end){
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                // 前半段有序段
                if (target < nums[mid] && target >= nums[start]) {
                    // 在前半段有序段中
                    if (start < mid) {
                        return search(nums, start,mid - 1, target);
                    }
                } else {
                    // 在后半段有序段中
                    if (mid < end) {
                        return search(nums, mid + 1, end, target);
                    }
                }
            }
            if (nums[mid] <= nums[end]) {
                // 后半段有序段
                if (target > nums[mid] && target <= nums[end]) {
                    // 在后半段有序段中
                    if (mid < end) {
                        return search(nums, mid + 1, end, target);
                    }
                } else {
                    // 在前半段有序段中
                    if (start < mid) {
                        return search(nums, start,mid - 1, target);
                    }
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        // nums = [4,5,6,7,8,1,2,3], target = 8
        // [4,5,6,7,0,1,2] 0
        int[] nums = new int[]{5, 1, 3};
        int target = 5;
        int index = new Search().search(nums, target);
        System.out.println("index = " + index);
    }
}
